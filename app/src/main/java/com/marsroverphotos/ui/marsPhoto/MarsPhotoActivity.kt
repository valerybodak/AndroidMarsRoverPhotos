package com.marsroverphotos.ui.marsPhoto

import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.mars_photos_activity.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.marsroverphotos.R
import com.marsroverphotos.entities.Status
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import com.marsroverphotos.ui.roverSettings.RoverSettingsActivity
import android.app.Activity

class MarsPhotoActivity : AppCompatActivity() {

    private val PHOTO_GALLERY_COLUMNS_NUMBER = 2
    private val OPEN_SETTINGS_REQUEST_CODE = 1

    private val photosViewModel: MarsPhotoViewModel by viewModel()
    private lateinit var listAdapter: MarsPhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mars_photos_activity)
        listAdapter = MarsPhotoAdapter()

        recycler_view.layoutManager = GridLayoutManager(this, PHOTO_GALLERY_COLUMNS_NUMBER)
        recycler_view.adapter = listAdapter
        photosViewModel.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        photosViewModel.getMarsPhotoLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                    Log.e("Status.ERROR","Status.ERROR")
                }
                Status.LOADING -> {
                    //Progress
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                listAdapter.updateList(response.photos)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.getItemId()) {
            R.id.menu_item_settings   //this item has your app icon
            -> {
                val i = Intent(this, RoverSettingsActivity::class.java)
                startActivityForResult(i, OPEN_SETTINGS_REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == OPEN_SETTINGS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data!!.getStringExtra("result")
            }
        }
    }
}