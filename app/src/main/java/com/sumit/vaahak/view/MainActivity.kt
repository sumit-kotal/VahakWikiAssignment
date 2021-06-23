package com.sumit.vaahak.view

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.sumit.vaahak.databinding.ActivityMainBinding
import com.sumit.vaahak.model.Pages
import com.sumit.vaahak.utils.MySuggestionProvider
import com.sumit.vaahak.utils.SearchResultAdapter
import com.sumit.vaahak.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val ROTATE_ANIMATION_DURATION = 800L
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    var pagesResult = mutableListOf<Pages>()

    @ObsoleteCoroutinesApi
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                SearchRecentSuggestions(this, MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE)
                    .saveRecentQuery(query, null)
            }
        }

        mainViewModel.searchResponse.observe(this,{response ->
            pagesResult = response.query.pages.toMutableList()
            showRecycler()
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoadingScreen()
                mainViewModel.getSearchResults(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    private fun showRecycler(){
        binding.progressImage.visibility = View.VISIBLE
        val transition = Slide(Gravity.BOTTOM)
        transition.duration = 2000
        transition.addTarget(binding.recyclerView)
        TransitionManager.beginDelayedTransition(binding.rootview as ViewGroup, transition)
        binding.recyclerView.visibility = View.VISIBLE
        val adapter = SearchResultAdapter(this,pagesResult)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun showLoadingScreen() {
        binding.recyclerView.visibility = View.GONE
        binding.progressImage.visibility = View.VISIBLE
        val rotate = RotateAnimation(
            0F, 360F,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = ROTATE_ANIMATION_DURATION
        rotate.repeatCount = Animation.INFINITE
        binding.progressImage.startAnimation(rotate)
    }

}