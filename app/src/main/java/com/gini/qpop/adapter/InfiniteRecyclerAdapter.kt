package com.gini.qpop.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gini.qpop.R
import com.gini.qpop.model.Data

class InfiniteRecyclerAdapter(originalList: List<Data>)
    : RecyclerView.Adapter<InfiniteRecyclerAdapter.InfiniteRecyclerViewHolder>() {

    private val newList: List<Data> =
        listOf(originalList.last()) + originalList + listOf(originalList.first())

    class InfiniteRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(dataView: Data) {
            val pagerTextView: TextView = itemView.findViewById(R.id.pagerTextView)
            val pagerCardView: CardView = itemView.findViewById(R.id.pagerCardView)

            pagerTextView.text = dataView.question
            pagerCardView.setBackgroundColor(Color.parseColor(dataView.color))
        }

        companion object {
            fun from(parent: ViewGroup) : InfiniteRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(
                    R.layout.custom_infinite_pager_layout,
                    parent, false)
                return InfiniteRecyclerViewHolder(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteRecyclerViewHolder {
        return InfiniteRecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: InfiniteRecyclerViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int {
        return newList.size
    }

}