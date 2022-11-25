package com.example.myapplication.rank

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.auth.User
import kotlinx.android.synthetic.main.item_rank.view.*

class RankViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v

    fun bind(item: User){
        view.mName.text = item.nickName
        view.mMileage.text = item.mileage.toString()
        view.visitButton.setOnClickListener {
            val intent = Intent(view.context, VisitActivity::class.java)
            intent.putExtra("uid", item.uid)
            view.context.startActivity(intent)
        }
    }
}