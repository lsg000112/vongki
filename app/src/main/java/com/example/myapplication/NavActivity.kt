package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLobbyBinding
import com.example.myapplication.history.HistoryActivity
import com.example.myapplication.ocr.OCRActivity
import com.example.myapplication.pet.PetFragmentActivity
import com.example.myapplication.rank.RankActivity
import com.example.myapplication.shop.ShopCategoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class NavActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private val userCollectionRef = Firebase.firestore.collection("users")
    private lateinit var binding: ActivityLobbyBinding
    lateinit var uid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        uid = auth!!.uid.toString()
        overridePendingTransition(R.anim.anim_slide_left, R.anim.anim_slide_right)
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
                println("current mileage : " + snapshot.data?.get("currentMileage"))
                binding.mileageTextView.text = snapshot.data?.get("currentMileage").toString()
                val ments = arrayOf("오늘도 힘찬 하루 보내세요!", "당신의 봉사생활을 응원해요!", "오늘도 너무 고생 많았어요!", "당신의 선택을 믿고 힘내세요!", "실수를 두려워하지 마세요!", "지금도 잘하고 있어요!")
                val ment = Random.nextInt(ments.size)
                if(snapshot.data?.get("currentMileage") == 0){
                    binding.userTextView.text = snapshot.data?.get("nickName").toString() + "님 환영해요!\n봉사확인서를 업로드하고 마일리지를 받아봐요"
                }else{
                    binding.userTextView.text = snapshot.data?.get("nickName").toString() + "님 반가워요,\n" + ments[ment]
                }

            }
        }

        //로그아웃
        binding.logoutLayout.setOnClickListener {
//            binding.logOutButton.setOnClickListener {
                //val intent = Intent(this, LoginActivity::class.java)
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                //startActivity(intent)
            val builder = AlertDialog.Builder(this)
            val dialogView: View = layoutInflater.inflate(R.layout.dialog_buy, null)
            builder.setView(dialogView)
            val alertDialog = builder.create()
            dialogView.findViewById<TextView>(R.id.buyDialogText).text = "로그아웃 하시겠어요?"
            dialogView.findViewById<Button>(R.id.buyOk)
            alertDialog.show()
            val ok_btn = dialogView.findViewById<Button>(R.id.buyOk)
            ok_btn.setOnClickListener {
                    auth?.signOut()
                    pref.edit().remove("isFirst").apply()
                    Toast.makeText(this, "로그아웃됐어요.", Toast.LENGTH_LONG).show()
                    alertDialog.dismiss()
                    finish()
            }
            val cancel_button = dialogView.findViewById<Button>(R.id.buyCancel)
            cancel_button.setOnClickListener {
                alertDialog.dismiss()
            }

//            }
        }
        binding.getMileageButton.setOnClickListener{  //마일리지 적립
            currentUser.get().addOnSuccessListener { document ->
                if(document != null){
                    userCollectionRef.document(auth!!.uid.toString()).update("mileage", (1 + Integer.parseInt(document.data?.get("mileage").toString())))
                    userCollectionRef.document(auth!!.uid.toString()).update("currentMileage", (1 + Integer.parseInt(document.data?.get("currentMileage").toString())))
                }else
                    println("failed")
            }
        }

        binding.historyLayout.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_slide_left, R.anim.anim_slide_right)
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

        setContentView(binding.root)
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