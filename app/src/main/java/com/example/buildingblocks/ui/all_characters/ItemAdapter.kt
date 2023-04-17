package com.example.buildingblocks.ui.all_characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buildingblocks.Item
import com.example.buildingblocks.databinding.ItemLayoutBinding

class ItemAdapter(val items : List<Item>, val callBck: ItemListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()  {

    interface ItemListener {
        fun onItemClicked(index:Int)
        fun onItemLongClicked(index:Int)
    }

    inner class ItemViewHolder(private  val binding : ItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            callBck.onItemClicked(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            callBck.onItemLongClicked(adapterPosition)
            return false
        }

        fun bind(item : Item){
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.description
            binding.StockPrice.text = item.price
            Glide.with(binding.root).load(item.photo).circleCrop().into(binding.itemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}