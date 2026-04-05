package com.example.buildingblocks.ui.single_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.buildingblocks.Item
import com.example.buildingblocks.databinding.StockDetailsBinding
import com.example.buildingblocks.ui.ItemsViewModel

class StockDetails : Fragment() {

    var _binding: StockDetailsBinding? = null
    val binding get() = _binding!!

    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StockDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        val item = arguments?.getParcelable<Item>("item")
        item?.let {
            binding.itemTitle.text = it.title
            binding.itemDesc.text = it.description
            binding.itemPrice.text = it.price
            Glide.with(this).load(it.photo).circleCrop().into(binding.stockImage)

            binding.newsProgress.visibility = View.VISIBLE
            viewModel.fetchNews(it.title)
        }

        viewModel.news.observe(viewLifecycleOwner) { newsList ->
            binding.newsProgress.visibility = View.GONE
            if (newsList.isEmpty()) {
                binding.noNewsText.visibility = View.VISIBLE
                binding.newsRecycler.visibility = View.GONE
            } else {
                binding.noNewsText.visibility = View.GONE
                binding.newsRecycler.visibility = View.VISIBLE
                binding.newsRecycler.layoutManager = LinearLayoutManager(requireContext())
                binding.newsRecycler.adapter = NewsAdapter(newsList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
