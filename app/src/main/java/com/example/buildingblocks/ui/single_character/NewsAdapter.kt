package com.example.buildingblocks.ui.single_character

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buildingblocks.data.network.NewsItem
import com.example.buildingblocks.databinding.NewsItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsAdapter(private val items: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsItem) {
            binding.newsTitle.text = item.title
            binding.newsPublisher.text = item.publisher
            val date = Date(item.providerPublishTime * 1000)
            binding.newsTime.text = SimpleDateFormat("MMM d, HH:mm", Locale.getDefault()).format(date)
            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}
