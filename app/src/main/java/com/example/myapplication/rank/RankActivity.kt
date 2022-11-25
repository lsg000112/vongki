package com.example.myapplication.rank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.auth.User
import com.example.myapplication.databinding.ActivityRankBinding
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Query.Direction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RankActivity : AppCompatActivity(){
    private val userCollectionRef = Firebase.firestore.collection("users")
    private lateinit var binding: ActivityRankBinding
    private lateinit var ranking : Query

    var rankList : List<User> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ranking = userCollectionRef.orderBy("mileage", Direction.DESCENDING)
        ranking.get().addOnSuccessListener { result ->
            for(i in result){
                rankList = rankList + User(
                    i.get("uid") as String,
                    i.get("name") as String,
                    i.get("nickName") as String,
                    (i.get("mileage") as Long).toInt())
            }
            val adapter = RankListAdapter(rankList)
            println(rankList)
            binding.recyclerView.adapter = adapter
        }
    //@TODO https://dalgonakit.tistory.com/138
    }

}