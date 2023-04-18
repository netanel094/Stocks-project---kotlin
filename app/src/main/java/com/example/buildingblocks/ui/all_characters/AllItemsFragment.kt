package com.example.buildingblocks.ui.all_characters
import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buildingblocks.Item
import com.example.buildingblocks.R
import com.example.buildingblocks.databinding.AllItemsBinding
import com.example.buildingblocks.ui.ItemsViewModel

class AllItemsFragment :Fragment() {

    private var _binding: AllItemsBinding? = null
    private val binding get() = _binding!!


    private val viewModel : ItemsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.items?.observe(viewLifecycleOwner)
        {

            binding.recycler.adapter = ItemAdapter(it, object : ItemAdapter.ItemListener {
                override fun onItemClicked(index: Int) {
                    val bundle = bundleOf("item" to index)
                    findNavController().navigate(R.id.action_allItemsFragment_to_stockDetails, bundle)
                }

                override fun onItemLongClicked(index: Int) {}
            })
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        }




        ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE,ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val context = binding.root.context
                AlertDialog.Builder(context)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete this item?")
                    .setPositiveButton("Delete") { _, _ ->
                        //ItemMenager.remove(viewHolder.adapterPosition)
                        // binding.recycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                        binding.recycler.adapter!!.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }).attachToRecyclerView(binding.recycler)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val symbols = mutableListOf<String>("AAPL", "NVDA", "AMZN", "TSLA", "MBLY", "GOOGL",
        "META", "CVX", "NFLX", "TEX")
        val companyName = mutableListOf<String>("Apple", "Nvidia", "Amazon", "Tesla", "Mobilye", "Google", "Meta", "Chevron", "Netflix", "Terex")
        val prices = mutableListOf<String>("23.54$", "56.90$", "112.222$", "12.34$", "90.89$", "12.56$", "340.23$", "5.98$", "45.83$", "28.54$")
        val logos = mutableListOf<Uri>(
            Uri.parse("android.resource://com.example.buildingblocks/drawable/apple"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/nvidia"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/amazon"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/tesla"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/mobileye"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/google"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/meta"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/cvx"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/netflix"),
            Uri.parse("android.resource://com.example.buildingblocks/drawable/tex"),
        )
        if (viewModel.items?.value?.isNotEmpty() == true) {
            for (i in symbols.indices) {
                val item = Item (
                    symbols[i],
                    companyName[i],
                    prices[i],
                    logos[i],
                )
                viewModel.addItem(item)
            }
        }
    }

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllItemsBinding.inflate(inflater, container, false)
        binding.addStock.setOnClickListener {
            findNavController().navigate(R.id.action_allItemsFragment_to_addItemFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}