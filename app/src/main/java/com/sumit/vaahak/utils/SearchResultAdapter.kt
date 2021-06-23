package com.sumit.vaahak.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import com.sumit.vaahak.R
import com.sumit.vaahak.databinding.PageItemBinding
import com.sumit.vaahak.model.Pages
import com.sumit.vaahak.view.WebViewActivity
import timber.log.Timber

class SearchResultAdapter(val activity: Activity, private val pagesResult: MutableList<Pages>) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val page = pagesResult[position]
            val webUrl = "http://en.wikipedia.org/?curid=${page.pageid}"

            var imageURL = page.thumbnail?.source
            val description = page.terms?.description?.get(0)

            binding.itemTitle.text = page.title

            if (description != null)
                binding.itemDescription.text = "Description: ${description}"
            else
                binding.itemDescription.text = "No description available"

            if(imageURL == null )
                imageURL = "https://www.ncenet.com/wp-content/uploads/2020/04/no-image-png-2.png"

            binding.itemImage.load(imageURL) {
                crossfade(true)
                memoryCachePolicy(CachePolicy.ENABLED)
                error(android.R.drawable.ic_menu_gallery)
                scale(Scale.FILL)
                data(imageURL)
                placeholder(R.mipmap.ic_launcher)
                networkCachePolicy(CachePolicy.ENABLED)
            }

            binding.itemWebsite.setOnClickListener {
                val intent = Intent(activity.applicationContext,WebViewActivity::class.java)
                intent.putExtra("url",webUrl)
                activity.startActivity(intent)
            }
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return pagesResult.size
    }

    //this method binds views to position
    override fun getItemViewType(position: Int): Int {
        return position
    }

    //the class is holding the list view and item click listener
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PageItemBinding.bind(view)
    }


}
