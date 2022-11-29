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
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityShopBuyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ShopBuyActivity :AppCompatActivity(){
    private lateinit var binding: ActivityShopBuyBinding
    private var auth : FirebaseAuth? = null
    private val userCollectionRef = Firebase.firestore.collection("users")
    lateinit var currentUser : DocumentReference
    val categories = arrayOf("", "roof", "wall", "window", "sofa", "plant", "prop", "wallpaper", "curtain")
    var category = 1

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShopBuyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        currentUser = userCollectionRef.document(auth!!.uid.toString())

        currentUser.addSnapshotListener { snapshot, e ->
            binding.mileageTextView.text = snapshot?.data?.get("currentMileage").toString()
        }

        binding.backButton.setOnClickListener{finish()}

        val intent = intent
        category = intent.getIntExtra("category", 1)
        binding.categoryText.text = resources.getString(resources.getIdentifier("category$category", "string", packageName))

        if(category == 1) {
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.halfroof1))
            binding.item1.setPadding(40, 40, 40, 40)
            binding.item2.setPadding(40, 40, 40, 40)
            binding.item3.setPadding(40, 40, 40, 40)
            binding.item4.setPadding(40, 40, 40, 40)
            binding.item5.setPadding(40, 40, 40, 40)
            binding.item6.setPadding(40, 40, 40, 40)
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.halfroof2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.halfroof3))
            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.halfroof4))
            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.halfroof5))
            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.halfroof6))
        }else if(category == 2) {
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.block1))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.block2))
            binding.item2.setPadding(33, 33, 33, 33)
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.block3))
            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.block4))
            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.block5))
            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.block6))
        }else if(category == 3){
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.window1))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.window2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.window3))
            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.window4))
            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.window5))
            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.window6))
        }else if(category == 4){
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.sofa1))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.sofa2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.sofa3))
            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.sofa4))
            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.sofa5))
            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.sofa6))
        }else if(category == 5){
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.plant1))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.plant2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.plant3))
            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.plant4))
            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.plant5))
            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.plant6))
        }else if(category == 6){
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.clock))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.clock2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.clock3))
//            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.curtain1))
//            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.curtain2))
//            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.curtain3))
            binding.gridLayout2.visibility = View.INVISIBLE
        }else if(category == 7){
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.wallpapericon1))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.wallpapericon2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.wallpapericon3))
            binding.item4.setImageDrawable(resources.getDrawable(R.drawable.wallpapericon4))
            binding.item5.setImageDrawable(resources.getDrawable(R.drawable.wallpapericon5))
            binding.item6.setImageDrawable(resources.getDrawable(R.drawable.wallpapericon6))
        }else if(category == 8){
            binding.item1.setImageDrawable(resources.getDrawable(R.drawable.curtain1))
            binding.item2.setImageDrawable(resources.getDrawable(R.drawable.curtain2))
            binding.item3.setImageDrawable(resources.getDrawable(R.drawable.curtain3))
            binding.gridLayout2.visibility = View.INVISIBLE
        }

        var id: Int
        val c : String = categories[category]
        for(i: Int in 1..6){
            if(category == 6 || category == 8)
                if(i > 3)
                    break
            id = resources.getIdentifier("item$i","id", packageName)
              binding.root.findViewById<View?>(id)
                    .setOnClickListener {
                        println("price = " + i*10)
                        var mileage = 0
                        var current = 0

                        currentUser.get().addOnSuccessListener { document ->
                            var isWindow : List<String> = listOf()
                            if(categories[category] == "curtain") {
                                isWindow = document.get("window") as List<String>
                            }
                            if(document != null){
                                val builder = AlertDialog.Builder(this)
                                mileage = Integer.parseInt(document.get("currentMileage").toString())
                                current = Integer.parseInt(document.get("n" + c).toString())
                                val array : List<String> = document.get(categories[category]) as List<String>
                                println("mileage = " + mileage)
                                if(current == i) {
                                    val dialogView: View = layoutInflater.inflate(R.layout.dialog_alert, null)
                                    builder.setView(dialogView)
                                    val alertDialog = builder.create()
                                    dialogView.findViewById<TextView>(R.id.buyDialogText).text = "이미 적용된 아이템이에요!"
                                    dialogView.findViewById<Button>(R.id.buyOk)
                                    alertDialog.show()
                                    val ok_btn = dialogView.findViewById<Button>(R.id.buyOk)
                                    ok_btn.setOnClickListener {
                                        alertDialog.dismiss()
                                    }
                                }
                                else if(array.contains(categories[category] + i.toString())) {
                                    val dialogView: View = layoutInflater.inflate(R.layout.dialog_buy, null)
                                    builder.setView(dialogView)
                                    val alertDialog = builder.create()
                                    dialogView.findViewById<TextView>(R.id.buyDialogText).text = "가지고 있는 아이템이에요!\n이 아이템을 적용할까요?"
                                    dialogView.findViewById<Button>(R.id.buyOk)
                                    alertDialog.show()
                                    val ok_btn = dialogView.findViewById<Button>(R.id.buyOk)
                                    ok_btn.setOnClickListener {
                                        currentUser.update("n" + c, i).addOnSuccessListener {
                                            Toast.makeText(this, "적용되었어요", Toast.LENGTH_SHORT).show()
                                            alertDialog.dismiss()
                                        }
                                    }
                                    val cancel_button = dialogView.findViewById<Button>(R.id.buyCancel)
                                    cancel_button.setOnClickListener {
                                        alertDialog.dismiss()
                                    }
                                }else if(categories[category] == "curtain" && isWindow.isEmpty()){
                                    val dialogView: View = layoutInflater.inflate(R.layout.dialog_alert, null)
                                    builder.setView(dialogView)
                                    val alertDialog = builder.create()
                                    dialogView.findViewById<TextView>(R.id.buyDialogText).text = "커튼은 창문이 있어야\n구매할 수 있어요!"
                                    dialogView.findViewById<Button>(R.id.buyOk)
                                    alertDialog.show()
                                    val ok_btn = dialogView.findViewById<Button>(R.id.buyOk)
                                    ok_btn.setOnClickListener {
                                        alertDialog.dismiss()
                                    }
                                }
                                else if(mileage >= i*10) {
                                    custom_dialog(this, i * 10)
                                }else {
                                    val dialogView: View = layoutInflater.inflate(R.layout.dialog_alert, null)
                                    builder.setView(dialogView)
                                    val alertDialog = builder.create()
                                    dialogView.findViewById<TextView>(R.id.buyDialogText).text = "마일리지가 부족해요!"
                                    dialogView.findViewById<Button>(R.id.buyOk)
                                    alertDialog.show()
                                    val ok_btn = dialogView.findViewById<Button>(R.id.buyOk)
                                    ok_btn.setOnClickListener {
                                        alertDialog.dismiss()
                                    }
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
                dialogView.findViewById<TextView>(R.id.buyDialogText).text = "현재 남은 마일리지는 " + mileage + " 입니다.\n정말 구입하시겠어요?"
                val itemValue : String = categories[category] + Integer.toString(price/10)
                val ok_btn = dialogView.findViewById<Button>(R.id.buyOk)
                ok_btn.setOnClickListener {
                    currentUser.update(categories[category], FieldValue.arrayUnion(itemValue)).addOnSuccessListener {
                        currentUser.update("currentMileage", mileage - price).addOnSuccessListener {
                            alertDialog.dismiss()
                            val dialogView2: View = layoutInflater.inflate(R.layout.dialog_buy, null)
                            builder.setView(dialogView2)
                            val alertDialog2 = builder.create()
                            dialogView2.findViewById<TextView>(R.id.buyDialogText).text = "구매가 완료되었어요.\n바로 적용할까요?"
                            dialogView2.findViewById<Button>(R.id.buyOk)
                            alertDialog2.show()
                            val ok_btn2 = dialogView2.findViewById<Button>(R.id.buyOk)
                            ok_btn2.setOnClickListener {
                                var c : String
                                if(category == 6 && price/10 > 3 ){
                                    c = "curtain"
                                }else if(category == 6 && price <= 3){
                                    c = "prop"
                                }else{
                                    c = categories[category]
                                }
                                currentUser.update("n" + c, price/10).addOnSuccessListener {
                                    Toast.makeText(this, "적용되었어요", Toast.LENGTH_SHORT).show()
                                    alertDialog2.dismiss()
                                }
                            }
                            val cancel_button2 = dialogView2.findViewById<Button>(R.id.buyCancel)
                            cancel_button2.setOnClickListener {
                                alertDialog2.dismiss()
                            }
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
}