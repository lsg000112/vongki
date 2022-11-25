package com.example.myapplication.pet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.reflect.typeOf

class PetFragmentActivity : Fragment() {
    private lateinit var binding:FragmentPetBinding
    private var auth : FirebaseAuth? = null
    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = Firebase.auth
        val uid = arguments?.getString("uid")
        val currentUser = userCollectionRef.document(uid!!)

        currentUser.addSnapshotListener{ snapshot, e ->
            if (e != null) {
                println("mileage change listen failed")
            }
            if (snapshot != null && snapshot.exists()) {
                val mileage = Integer.parseInt(snapshot.data?.get("mileage").toString())
                val petType = Integer.parseInt(snapshot.data?.get("petType").toString())
                println("petType = " + petType)
                if(petType == 1){
                    if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog1))
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog2))
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog3))
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg))
                    }
                }else if(petType == 2){
                    if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat1))
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat2))
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat3))
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg))
                    }
                }else if(petType == 3){
                    if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog1))
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog2))
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog3))
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg))
                    }
                }
            }
        }
        return view //inflater.inflate(R.layout.fragment_pet, container, false)
    }
}