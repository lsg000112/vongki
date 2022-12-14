package com.example.myapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.firstrun.EggSelectActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private var signup_okButton : Button? = null
    private var signupID : EditText? = null
    private var signupPassword : EditText? = null
    private lateinit var binding: ActivityRegisterBinding
    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        signup_okButton = binding.button
        signupID = binding.editTextTextEmailAddress
        signupPassword = binding.editTextTextPassword
        signup_okButton!!.setOnClickListener {
            val id = signupID!!.text.toString()
            val pw = signupPassword!!.text.toString()
            val name = binding.editTextTextPersonName.text.toString()
            val nickName = binding.editTextTextNickName.text.toString()
            createAccount(id, pw, name, nickName)
        }
    }

    private fun createAccount(email: String, password: String, name: String, nickName: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        saveUser(User(auth!!.uid.toString(), name, nickName))
                        Toast.makeText(
                            this, "?????? ?????? ??????.",
                            Toast.LENGTH_SHORT
                        ).show()

                        val pref = applicationContext.getSharedPreferences("isFirst", MODE_PRIVATE)
                        pref.edit().putBoolean("isFirst", false).apply() //??????????????? ???????????? ?????? ????????? ??????


                        val intent = Intent(this, EggSelectActivity::class.java)
                        startActivity(intent)

                        finish() // ????????? ??????
                    } else {
                        Toast.makeText(
                            this, "?????? ?????? ??????",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun saveUser(user: User) = CoroutineScope(Dispatchers.IO).launch{
        try {
            userCollectionRef.document(user.uid).set(user).await() // await??? ???????????? ??????????????? ???????????? ??? ??? ?????? ??????????????? ??????????????????.
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "????????? ????????? ??????!", Toast.LENGTH_LONG).show()
                println("????????? ????????? ??????")
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                println(e.message)
            }
        }
    }
}
