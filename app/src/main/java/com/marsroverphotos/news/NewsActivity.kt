package com.marsroverphotos.news

import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.news_articles.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.marsroverphotos.R
import com.marsroverphotos.entities.Status
import com.marsroverphotos.news.NewsListAdapter

class NewsActivity : AppCompatActivity() {

    private val newsList: NewsViewModel by viewModel()
    private lateinit var listAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_articles)
        listAdapter = NewsListAdapter()

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = listAdapter
        newsList.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        newsList.getNewsLiveData().observe(this, Observer {
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
                listAdapter.updateList(response.articles)
            }
        })
    }
}