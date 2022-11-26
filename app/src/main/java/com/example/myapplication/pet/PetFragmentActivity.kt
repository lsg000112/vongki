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
    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetBinding.inflate(inflater, container, false)
        val view = binding.root
        val uid = arguments?.getString("uid")
        val currentUser = userCollectionRef.document(uid!!)

        currentUser.addSnapshotListener{ snapshot, e ->
            if (e != null) {
                println("mileage change listen failed")
            }
            if (snapshot != null && snapshot.exists()) {
                val mileage = Integer.parseInt(snapshot.data?.get("mileage").toString())
                val petType = Integer.parseInt(snapshot.data?.get("petType").toString())
                val sofa = Integer.parseInt(snapshot.data?.get("sofa").toString())
                val window = Integer.parseInt(snapshot.data?.get("window").toString())
                val plant = Integer.parseInt(snapshot.data?.get("plant").toString())
                val prop = Integer.parseInt(snapshot.data?.get("prop").toString())
                val roof = Integer.parseInt(snapshot.data?.get("roof").toString())
                val wall = Integer.parseInt(snapshot.data?.get("wall").toString())
                println("petType = " + petType)
                if(petType == 1){
                    if(mileage > 50){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat5))
                    }else if(mileage > 40){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat4))
                    }else if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat3))
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat2))
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat1))
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg1))
                    }
                }else if(petType == 2){
                    if(mileage > 50){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog5))
                    }else if(mileage > 40){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog4))
                    }else if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog3))
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog2))
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog1))
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg2))
                    }
                }else if(petType == 3){
                    if(mileage > 50){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit5))
                    }else if(mileage > 40){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit4))
                    }else if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit3))
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit2))
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit1))
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg3))
                    }
                }

                if(sofa == 1){
                    binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa))
                }else if(sofa == 2){
                    binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa))
                }else if(sofa == 3){
                    binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa))
                }else if(sofa == 4){
                    binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa))
                }else if(sofa == 5){
                    binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa))
                }else if(sofa == 6){
                    binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa))
                }

                if(window == 1){
                    binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window))
                }else if(window == 2){
                    binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window))
                }else if(window == 3){
                    binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window))
                }else if(window == 4){
                    binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window))
                }else if(window == 5){
                    binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window))
                }else if(window == 6){
                    binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window))
                }

                if(wall == 1){
                    binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall))
                }else if(wall == 2){
                    binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall))
                }else if(wall == 3){
                    binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall))
                }else if(wall == 4){
                    binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall))
                }else if(wall == 5){
                    binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall))
                }else if(wall == 6){
                    binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall))
                }

                if(plant == 1){
                    binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                }else if(wall == 2){
                    binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                }else if(wall == 3){
                    binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                }else if(wall == 4){
                    binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                }else if(wall == 5){
                    binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                }else if(wall == 6){
                    binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                }

                if(roof == 1){
                    binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof))
                }else if(wall == 2){
                    binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof))
                }else if(wall == 3){
                    binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof))
                }else if(wall == 4){
                    binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof))
                }else if(wall == 5){
                    binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof))
                }else if(wall == 6){
                    binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof))
                }

                if(prop == 1){
                    binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                }else if(wall == 2){
                    binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                }else if(wall == 3){
                    binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                }else if(wall == 4){
                    binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                }else if(wall == 5){
                    binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                }else if(wall == 6){
                    binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                }
            }
        }
        return view //inflater.inflate(R.layout.fragment_pet, container, false)
    }
}