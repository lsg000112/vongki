package com.example.myapplication.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.auth.User
import com.example.myapplication.databinding.ActivityHistoryBinding
import com.example.myapplication.databinding.ActivityRankBinding
import com.example.myapplication.rank.RankListAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HistoryActivity : AppCompatActivity() {
    private val userCollectionRef = Firebase.firestore.collection("users")
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var ranking : Query
    var rankList : List<History> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener{finish()}
        val auth = Firebase.auth



        ranking = userCollectionRef.orderBy("mileage", Query.Direction.DESCENDING)

        userCollectionRef.document(auth.uid!!).get().addOnSuccessListener { document ->
            binding.totalMileageText.text = "누적 마일리지 : " + document.get("mileage")
            binding.nowMileageText.text = "현재 마일리지 : " + document.get("currentMileage")
            val whereArray: List<String> = document.get("whereRecord") as List<String>
            val timeArray: List<String> = document.get("timeRecord") as List<String>

            if (whereArray.isEmpty()){
                rankList = rankList + History("봉사 기록이 없어요.", "봉사확인서를 등록하고\n마일리지를 받아요!")
            }
            for (i : Int in whereArray.indices){
                rankList = rankList + History(whereArray[i], timeArray[i])
            }
            val adapter = HistoryListAdapter(rankList)
            println(adapter.itemList)
            binding.recyclerView.adapter = adapter
        }

        setContentView(binding.root)
    }
}