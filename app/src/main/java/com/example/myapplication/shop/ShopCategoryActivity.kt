package com.example.myapplication.shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityShopCategoryBinding

class ShopCategoryActivity :AppCompatActivity(){
    private lateinit var binding: ActivityShopCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShopCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{finish()}

        println(binding.category1)
        var id: Int
        for(i: Int in 1..6){
            id = resources.getIdentifier("category$i", "id", packageName)
            binding.root.findViewById<View?>(id)
                    .setOnClickListener {
                        val intent = Intent(this, ShopBuyActivity::class.java).putExtra("category", i)
                        startActivity(intent)
                }
        }
    }
}