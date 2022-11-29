package com.example.myapplication.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.auth.User

class HistoryListAdapter(val itemList : List<History>) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = itemList[position]
        println(item.whereRecord + item.timeRecord)
        holder.apply {
            bind(item.whereRecord, item.timeRecord)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}