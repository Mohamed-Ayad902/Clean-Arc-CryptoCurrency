package com.example.cleancryptocurrency.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleancryptocurrency.databinding.TagItemBinding

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.TagsVH>() {

    inner class TagsVH(val binding: TagItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagsVH(TagItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: TagsVH, position: Int) {
        holder.binding.apply {
            tvTag.text = differ.currentList[position]
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}