package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLobbyBinding
import com.example.myapplication.ocr.OCRActivity
import com.example.myapplication.pet.PetFragmentActivity
import com.example.myapplication.rank.RankActivity
import com.example.myapplication.shop.ShopCategoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NavActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private val userCollectionRef = Firebase.firestore.collection("users")
    private lateinit var binding: ActivityLobbyBinding
    lateinit var uid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth
        uid = auth!!.uid.toString()

        initTransactionEvent()


        val pref = applicationContext.getSharedPreferences("isFirst", MODE_PRIVATE)
        val currentUser = userCollectionRef.document(auth!!.uid.toString()) //현재 로그인중인 사용자
        val petType = applicationContext.getSharedPreferences("petType", MODE_PRIVATE)
        println(petType.getInt("petType", 1))


        currentUser.addSnapshotListener{ snapshot, e ->
            if (e != null) {
                println("mileage change listen failed")
            }
            if (snapshot != null && snapshot.exists()) {
                println("current mileage : " + snapshot.data?.get("mileage"))
                binding.mileageTextView.text = "현재 마일리지 : " + snapshot.data?.get("mileage")
                binding.userTextView.text = snapshot.data?.get("nickName").toString() + "님 환영합니다!"
            }
        }

        //로그아웃
        binding.logoutLayout.setOnClickListener {
//            binding.logOutButton.setOnClickListener {
                //val intent = Intent(this, LoginActivity::class.java)
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                //startActivity(intent)
                auth?.signOut()
                pref.edit().remove("isFirst").apply()
                Toast.makeText(this, "원활환 구동을 위해 애플리케이션을 종료합니다.", Toast.LENGTH_LONG).show()
                finish()
//            }
        }
        binding.getMileageButton.setOnClickListener{  //마일리지 적립
            currentUser.get().addOnSuccessListener { document ->
                if(document != null){
                    userCollectionRef.document(auth!!.uid.toString()).update("mileage", (1 + Integer.parseInt(document.data?.get("mileage").toString())))
                }else
                    println("failed")
            }
        }

        // ocr 액티비티
        binding.ocrLayout.setOnClickListener {
            val intent = Intent(this, OCRActivity::class.java)
            startActivity(intent)
        }


        binding.rankLayout.setOnClickListener{
            val intent = Intent(this, RankActivity::class.java)
            startActivity(intent)
        }

        binding.interiorLayout.setOnClickListener {
            val intent = Intent(this, ShopCategoryActivity::class.java)
            startActivity(intent)
        }


    }

    private fun initTransactionEvent(){
        val petFragment = PetFragmentActivity()
        val arguments = Bundle()
        arguments.putString("uid", uid)
        petFragment.arguments = arguments
        petFragment.setMenuVisibility(true)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, petFragment).commit()
    }


}