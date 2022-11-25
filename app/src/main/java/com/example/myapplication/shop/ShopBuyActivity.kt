package com.example.myapplication.shop

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityShopBuyBinding
import com.example.myapplication.databinding.ActivityShopCategoryBinding

class ShopBuyActivity :AppCompatActivity(){
    private lateinit var binding: ActivityShopBuyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShopBuyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = intent
        val category = intent.getIntExtra("category", 1)
        binding.categoryText.text = resources.getString(resources.getIdentifier("category$category", "string", packageName))

        var id: Int
        for(i: Int in 1..9){
            id = resources.getIdentifier("item$i","id", packageName)
              binding.root.findViewById<View?>(id)
                    .setOnClickListener {
                    println(i)
                }
        }
    }
}