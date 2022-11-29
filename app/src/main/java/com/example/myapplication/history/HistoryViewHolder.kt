package com.example.myapplication.history

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.auth.User
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_rank.view.*

class HistoryViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v

    fun bind(where : String, time : String){
        view.mWhere.text = where
        view.mTime.text = time
    }
}