package com.example.cleancryptocurrency.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleancryptocurrency.databinding.CoinItemBinding
import com.example.cleancryptocurrency.domain.models.Coin

class CoinsListAdapter(private val listener: OnCoinsClickListener) :
    RecyclerView.Adapter<CoinsListAdapter.CoinsListVH>() {

    inner class CoinsListVH(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CoinsListVH(CoinItemBinding.inflate(LayoutInflater.from(parent.context)))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinsListVH, position: Int) {
        holder.binding.apply {
            val coin = differ.currentList[position]
            tvName.text = coin.name
            tvRank.text = coin.rank.toString()
            if (coin.is_active)
                tvIsActive.text = "Active"
            else
                tvIsActive.text = "Inactive"
            holder.itemView.setOnClickListener { listener.onClick(coin) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnCoinsClickListener {
        fun onClick(coin: Coin)
    }

}