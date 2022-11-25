package com.example.myapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.NavActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.firstrun.TutorialActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(){
    private var auth : FirebaseAuth? = null
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        checkFirst()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth

        val registerButton = binding.registerButton
        val loginButton = binding.loginButton
        val idEditText = binding.editTextTextEmailAddress
        val passwordEditText = binding.editTextTextPassword

        // 회원가입 창으로
        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // 로그인 버튼
        loginButton.setOnClickListener {
            signIn(idEditText.text.toString(),passwordEditText.text.toString())
        }
    }

    // 로그아웃하지 않을 시 자동 로그인 , 회원가입시 바로 로그인 됨
    public override fun onStart() {
        super.onStart()
        println(auth!!.currentUser)
        moveMainPage(auth?.currentUser)
    }

    // 로그인
    private fun signIn(email: String, password: String) {
        val pref = applicationContext.getSharedPreferences("isFirst", MODE_PRIVATE)
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


    // 유저정보 넘겨주고 메인 액티비티 호출
    private fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this, NavActivity::class.java))
            finish()
        }
    }


    //최초 실행 확인
    private fun checkFirst(){
        val pref = applicationContext.getSharedPreferences("isFirst", MODE_PRIVATE)
        if(pref.getBoolean("isFirst", true)){
            pref.edit().putBoolean("isFirst", false).apply()
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}