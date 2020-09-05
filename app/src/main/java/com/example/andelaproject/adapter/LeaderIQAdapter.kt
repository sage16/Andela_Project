package com.example.andelaproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andelaproject.R
import com.example.andelaproject.model.LeaderIQItem
import kotlinx.android.synthetic.main.leaders_iq_list.view.*
import kotlinx.android.synthetic.main.learning_leaders_list.view.*

class LeaderIQAdapter : RecyclerView.Adapter<LeaderIQAdapter.LeaderIQViewHolder>() {


    inner class LeaderIQViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<LeaderIQItem>() {
        override fun areItemsTheSame(
            oldItem: LeaderIQItem,
            newItem: LeaderIQItem
        ): Boolean {
            return oldItem.score == newItem.score
        }

        override fun areContentsTheSame(
            oldItem: LeaderIQItem,
            newItem: LeaderIQItem
        ): Boolean {
            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderIQViewHolder {
        return LeaderIQViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.leaders_iq_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: LeaderIQViewHolder, position: Int) {
        val leaderIQItem = differ.currentList[position]
        val score = leaderIQItem.score.toString()
        val scoreNum = score + " skill IQ Score, "

        holder.itemView.apply {
            Glide.with(this).load(leaderIQItem.badgeUrl).into(ivLeaderIQBadge)
            tvLeaderIQCountry.text = leaderIQItem.country
            tvLeaderIQScore.text = scoreNum
            tvLeaderIQName.text = leaderIQItem.name
        }

    }

}