package com.marsroverphotos.ui.roverSettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marsroverphotos.R
import kotlinx.android.synthetic.main.rover_settings_activity.*
import android.app.Activity
import android.content.Intent
import com.module.domain.entities.RoverId

class RoverSettingsActivity : AppCompatActivity() {

    companion object{

        const val EXTRA_SELECTED_ROVER_ID = "extra_selected_rover_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rover_settings_activity)

        supportActionBar?.title = getString(R.string.select_rover)

        button_apply.setOnClickListener{

            val returnIntent = Intent()
            returnIntent.putExtra(EXTRA_SELECTED_ROVER_ID, getRoverId())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

        }
    }

    private fun getRoverId(): String {
        when (radio_group.checkedRadioButtonId) {
            R.id.radio_1 -> return RoverId.CURIOSITY.id
            R.id.radio_2 -> return RoverId.OPPORTUNITY.id
            R.id.radio_3 -> return RoverId.SPIRIT.id
        }
        return RoverId.CURIOSITY.id
    }
}