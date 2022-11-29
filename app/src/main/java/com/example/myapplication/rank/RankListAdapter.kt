package com.example.myapplication.rank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.auth.User
import kotlinx.android.synthetic.main.item_rank.view.*

class RankListAdapter(val itemList : List<User>) : RecyclerView.Adapter<RankViewHolder>() {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rank, parent, false)
        return RankViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item, position.toString())
        }
    }
}