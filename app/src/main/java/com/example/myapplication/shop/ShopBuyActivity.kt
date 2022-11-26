package com.example.myapplication.shop

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityShopBuyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class ShopBuyActivity :AppCompatActivity(){
    private lateinit var binding: ActivityShopBuyBinding
    private var auth : FirebaseAuth? = null
    private val userCollectionRef = Firebase.firestore.collection("users")
    lateinit var currentUser : DocumentReference
    val categories = arrayOf("roof", "wall", "window", "sofa", "plant", "clock")
    var category = 1

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShopBuyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        currentUser = userCollectionRef.document(auth!!.uid.toString())

        binding.backButton.setOnClickListener{finish()}

        val intent = intent
        category = intent.getIntExtra("category", 1)
        binding.categoryText.text = resources.getString(resources.getIdentifier("category$category", "string", packageName))


        var id: Int
        for(i: Int in 1..6){
            id = resources.getIdentifier("item$i","id", packageName)
              binding.root.findViewById<View?>(id)
                    .setOnClickListener {
                        println("price = " + i*10)
                        var mileage = 0
                        var status = 0
                        currentUser.get().addOnSuccessListener { document ->
                            if(document != null){
                                mileage = Integer.parseInt(document.get("currentMileage").toString())
                                status = Integer.parseInt(document.get(categories[category]).toString())
                                println("mileage = " + mileage)
                                if(i <= status) {
                                    Toast.makeText(this, "이미 가지고 있어요!", Toast.LENGTH_LONG).show()
                                }
                                else if(mileage > i*10) {
                                    custom_dialog(this, i * 10)
                                }else {
                                    Toast.makeText(this, "마일리지가 부족해요!", Toast.LENGTH_LONG).show()
                                }
                            }else{
                                println("정보 가져오기 오류")
                            }
                        }
                    }
        }
        setContentView(binding.root)

    }

    @SuppressLint("MissingInflatedId")
    fun custom_dialog(v: Context, price: Int) {
        val dialogView: View = layoutInflater.inflate(com.example.myapplication.R.layout.dialog_buy, null)
        val builder = AlertDialog.Builder(v)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        var mileage = 0
        currentUser.get().addOnSuccessListener { document ->
            if(document != null){
                mileage = Integer.parseInt(document.get("currentMileage").toString())
                println("mileage = " + mileage)
                dialogView.findViewById<TextView>(com.example.myapplication.R.id.buyDialogText).text = "현재 남은 마일리지는 " + mileage + " 입니다.\n정말 구입하시겠어요?"

                val ok_btn = dialogView.findViewById<Button>(com.example.myapplication.R.id.buyOk)
                ok_btn.setOnClickListener {
                    currentUser.update("currentMileage", mileage - price).addOnSuccessListener {
                        currentUser.update(categories[category], price / 10).addOnSuccessListener {
                            Toast.makeText(applicationContext, "구매가 완료되었습니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                    alertDialog.dismiss()
                }
            }else{
                println("정보 가져오기 오류")
            }
        }




        val cancle_btn = dialogView.findViewById<Button>(com.example.myapplication.R.id.buyCancel)
        cancle_btn.setOnClickListener { alertDialog.dismiss() }
    }

//TODO 현재 마일리지 보이는거 추가하기
}