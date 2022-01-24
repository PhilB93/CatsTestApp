package com.example.catstestapp.presentation.fragments.favoritesfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catstestapp.databinding.LayoutItemBinding
import com.example.catstestapp.presentation.fragments.listfragment.ListOfCatsListener
import com.example.catstestapp.presentation.model.CatUI
import com.example.catstestapp.util.loadImage

class FavoritesAdapter(private val listener: ListOfCatsListener) :
ListAdapter<CatUI, FavoritesAdapter.CatViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatViewHolder {
        return CatViewHolder(
            LayoutItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.CatViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindCat(it) }
    }

    inner class CatViewHolder(
        private val binding: LayoutItemBinding,
        private val listener: ListOfCatsListener
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bindCat(item: CatUI?) = with(binding) {
            item?.image?.let { it -> it.url?.let {imageView.loadImage(it)} }
            initButtonsListeners(item)
        }

        private fun initButtonsListeners(cat: CatUI?) {
            binding.cardItem.setOnClickListener {
                cat?.let { listener.onClick(it) }
            }
        }
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<CatUI>() {
        override fun areItemsTheSame(oldItem: CatUI, newItem: CatUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatUI, newItem: CatUI): Boolean {
            return oldItem == newItem
        }
    }
}