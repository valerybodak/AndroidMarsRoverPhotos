package com.marsroverphotos.marsPhoto

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

class MarsPhotoActivity : AppCompatActivity() {

    private val PHOTO_GALLERY_COLUMNS_NUMBER = 2

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
}