package com.example.myapplication.firstrun

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.auth.LoginActivity
import com.example.myapplication.auth.RegisterActivity
import com.example.myapplication.databinding.ActivityFirstrunBinding

class TutorialActivity : AppCompatActivity(){
    lateinit var binding : ActivityFirstrunBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityFirstrunBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.info.setOnClickListener {
            binding.infoTextView.visibility = View.VISIBLE
            binding.infoTextView.setHeight(30)
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun View.setHeight(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.height = value
            layoutParams = lp
        }
    }
}