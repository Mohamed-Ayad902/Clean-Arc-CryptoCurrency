package com.example.cleancryptocurrency.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleancryptocurrency.data.dto.TeamMember
import com.example.cleancryptocurrency.databinding.MemberItemBinding

class TeamAdapter :
    RecyclerView.Adapter<TeamAdapter.TeamVH>() {

    inner class TeamVH(val binding: MemberItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<TeamMember>() {
        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamVH(MemberItemBinding.inflate(LayoutInflater.from(parent.context)))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TeamVH, position: Int) {
        holder.binding.apply {
            val member = differ.currentList[position]
            tvName.text = member.name
            tvRole.text = member.position
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}