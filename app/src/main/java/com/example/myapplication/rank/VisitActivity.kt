package com.example.myapplication.rank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityVisitBinding
import com.example.myapplication.pet.PetFragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VisitActivity : AppCompatActivity() {
    private val userCollectionRef = Firebase.firestore.collection("users")
    private lateinit var binding: ActivityVisitBinding
    lateinit var uid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVisitBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        uid = intent.getStringExtra("uid")!!
        val currentUser = userCollectionRef.document(uid!!)
        currentUser.get().addOnSuccessListener {document ->
            if(document != null){
                binding.visitUserName.text = document.get("nickName").toString() + "'s Room"
            }else{
                println("다른 유저 이름 가져오기 실패")
            }
        }
        initTransactionEvent()
        setContentView(binding.root)
    }

    private fun initTransactionEvent(){
        val petFragment = PetFragmentActivity()
        val arguments = Bundle()
        arguments.putString("uid", uid)
        petFragment.arguments = arguments
        petFragment.setMenuVisibility(true)
        supportFragmentManager.beginTransaction().add(R.id.fragmentVisitView, petFragment).commit()
    }


}