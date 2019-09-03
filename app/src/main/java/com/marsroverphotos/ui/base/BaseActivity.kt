package com.marsroverphotos.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    protected fun getRootView(): View {
        return window.decorView.findViewById(android.R.id.content)
    }
    
    protected fun showError(message: String){
        Snackbar.make(getRootView(), message, Snackbar.LENGTH_SHORT).show()
    }

    protected fun showError(messageId: Int){
        showError(getString(messageId))
    }
}