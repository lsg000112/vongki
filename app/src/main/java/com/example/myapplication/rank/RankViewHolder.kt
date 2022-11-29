package com.example.myapplication.rank

import android.content.Intent
import android.transition.Visibility
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.auth.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_rank.view.*

class RankViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v

    fun bind(item: User, position: String){
        val auth = Firebase.auth
        view.mName.text = item.nickName
        view.mMileage.text = item.mileage.toString()
        println(item.nickName + "is rank" + position)
        if (position == "0") {view.rankIcon.setImageDrawable(this.itemView.context.resources.getDrawable(R.drawable.rank1))}
        else if (position == "1") {view.rankIcon.setImageDrawable(this.itemView.context.resources.getDrawable(R.drawable.rank2))}
        else if (position == "2") {view.rankIcon.setImageDrawable(this.itemView.context.resources.getDrawable(R.drawable.rank3))}
        else {view.rankIcon.setImageDrawable(this.itemView.context.resources.getDrawable(R.drawable.bear))}

        if (auth.uid == item.uid){
            view.visitButton.visibility = View.INVISIBLE
            view.mName.text = view.mName.text.toString() + " (나)"
        }else {
            view.visitButton.setOnClickListener {
                val intent = Intent(view.context, VisitActivity::class.java)
                intent.putExtra("uid", item.uid)
                view.context.startActivity(intent)
            }
        }
    }
}