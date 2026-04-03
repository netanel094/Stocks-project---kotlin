package com.example.buildingblocks.ui.single_character
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.buildingblocks.Item
import com.example.buildingblocks.databinding.StockDetailsBinding


class StockDetails : Fragment()
{
    var _binding : StockDetailsBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StockDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("DEPRECATION")
        val item = arguments?.getParcelable<Item>("item")
        item?.let {
            binding.itemTitle.text = it.title
            binding.itemDesc.text = it.description
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}