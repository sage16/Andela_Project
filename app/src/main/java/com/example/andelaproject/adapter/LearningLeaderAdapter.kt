package com.example.andelaproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andelaproject.R
import com.example.andelaproject.model.LearningLeaderItem
import kotlinx.android.synthetic.main.learning_leaders_list.view.*

class LearningLeaderAdapter : RecyclerView.Adapter<LearningLeaderAdapter.LearningLeaderViewHolder>() {


    inner class LearningLeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<LearningLeaderItem>() {
        override fun areItemsTheSame(
            oldItem: LearningLeaderItem,
            newItem: LearningLeaderItem
        ): Boolean {
            return oldItem.hours == newItem.hours
        }

        override fun areContentsTheSame(

            oldItem: LearningLeaderItem,
            newItem: LearningLeaderItem
        ): Boolean {
            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningLeaderViewHolder {
        return LearningLeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.learning_leaders_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: LearningLeaderViewHolder, position: Int) {
        val learningLeaderItem = differ.currentList[position]
        val hours = learningLeaderItem.hours.toString()
        val hourNum = hours + " learning hours, "
        holder.itemView.apply {
            Glide.with(this).load(learningLeaderItem.badgeUrl).into(ivLearningBadge)
            tvLearningCountry.text = learningLeaderItem.country
            tvLearningHour.text = hourNum
            tvLearningName.text = learningLeaderItem.name


        }
    }

}