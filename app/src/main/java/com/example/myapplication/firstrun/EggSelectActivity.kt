package com.example.myapplication.firstrun

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.NavActivity
import com.example.myapplication.databinding.ActivityEggselectBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.prefs.Preferences

class EggSelectActivity : AppCompatActivity(){
    private val userCollectionRef = Firebase.firestore.collection("users")
    private val auth = Firebase.auth
    lateinit var binding: ActivityEggselectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEggselectBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.firstEgg.setOnClickListener {
            userCollectionRef.document(auth.uid!!).update("petType", 1).addOnSuccessListener {
                val intent = Intent(this, NavActivity::class.java)
                startActivity(intent)
                finish()

            }

        }

        binding.secondEgg.setOnClickListener {
            userCollectionRef.document(auth.uid!!).update("petType", 2).addOnSuccessListener {
                val intent = Intent(this, NavActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.thirdEgg.setOnClickListener {
            userCollectionRef.document(auth.uid!!).update("petType", 3).addOnSuccessListener {
                val intent = Intent(this, NavActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}