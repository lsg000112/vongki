package com.example.myapplication.pet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPetBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
        val dm = resources.displayMetrics

        val param = ConstraintLayout.LayoutParams(
            Math.round(99*dm.density), Math.round(103*dm.density)
        )
        param.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        param.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        param.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        param.horizontalBias = 0.485f

        binding.petImage.setOnClickListener { v -> 
            val anim =  AnimationUtils.loadAnimation(activity, R.anim.egg_click)
            binding.petImage.animation = anim
            anim.setAnimationListener( object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    val anim2 = AnimationUtils.loadAnimation(activity, R.anim.egg_click2)
                    binding.petImage.animation = anim2
                    anim2.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(p0: Animation?) {
                        }

                        override fun onAnimationEnd(p0: Animation?) {
                            val anim3 = AnimationUtils.loadAnimation(activity, R.anim.egg_click3)
                            binding.petImage.animation = anim3
                        }

                        override fun onAnimationRepeat(p0: Animation?) {
                            TODO("Not yet implemented")
                        }

                    })
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }

            }
            )

            v.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.egg_click2))
        }


        currentUser.addSnapshotListener{ snapshot, e ->
            if (e != null) {
                println("mileage change listen failed")
            }
            if (snapshot != null && snapshot.exists()) {
                val mileage = Integer.parseInt(snapshot.data?.get("mileage").toString())
                val petType = Integer.parseInt(snapshot.data?.get("petType").toString())
                val sofa = Integer.parseInt(snapshot.data?.get("nsofa").toString())
                val window = Integer.parseInt(snapshot.data?.get("nwindow").toString())
                val plant = Integer.parseInt(snapshot.data?.get("nplant").toString())
                val prop = Integer.parseInt(snapshot.data?.get("nprop").toString())
                val curtain = Integer.parseInt(snapshot.data?.get("ncurtain").toString())
                val roof = Integer.parseInt(snapshot.data?.get("nroof").toString())
                val wall = Integer.parseInt(snapshot.data?.get("nwall").toString())
                val wallpaper = Integer.parseInt(snapshot.data?.get("nwallpaper").toString())
                println("petType = " + petType)
                if(petType == 1){
                    if(mileage > 50){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat5))
                        binding.petImage.setPadding(Math.round(10*dm.density))
                        param.topMargin = Math.round(305*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 40){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat4))
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat3))
                        binding.petImage.setPadding(Math.round(23*dm.density))
                        param.topMargin = Math.round(322*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat2))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        binding.petImage.layoutParams = param
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.cat1))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        binding.petImage.layoutParams = param
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg1))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(5*dm.density))
                        binding.petImage.layoutParams = param
                    }
                }else if(petType == 2){
                    if(mileage > 50){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog5))
                        binding.petImage.setPadding(Math.round(10*dm.density))
                        param.topMargin = Math.round(305*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 40){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog4))
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog3))
                        binding.petImage.setPadding(Math.round(23*dm.density))
                        param.topMargin = Math.round(322*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog2))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        binding.petImage.layoutParams = param
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.dog1))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        binding.petImage.layoutParams = param
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg2))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(5*dm.density))
                        binding.petImage.layoutParams = param
                    }
                }else if(petType == 3){
                    if(mileage > 50){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit5))
                        binding.petImage.setPadding(Math.round(10*dm.density))
                        param.topMargin = Math.round(305*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 40){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit4))
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 30){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit3))
                        binding.petImage.setPadding(Math.round(23*dm.density))
                        param.topMargin = Math.round(322*dm.density)
                        binding.petImage.layoutParams = param
                    }else if(mileage > 20){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit2))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        binding.petImage.layoutParams = param
                    }else if(mileage > 10){
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.rabit1))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(20*dm.density))
                        binding.petImage.layoutParams = param
                    }else{
                        binding.petImage.setImageDrawable(resources.getDrawable(R.drawable.egg3))
                        param.topMargin = Math.round(314*dm.density)
                        binding.petImage.setPadding(Math.round(5*dm.density))
                        binding.petImage.layoutParams = param
                    }
                }
                println("sofa = " + sofa)
                val paramSofa = ConstraintLayout.LayoutParams(
                    Math.round(76*dm.density), Math.round(88*dm.density)
                )
                paramSofa.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                paramSofa.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                paramSofa.leftMargin = Math.round(184*dm.density)
                when (sofa) {

                    1 -> {
                        binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa1))
                        binding.sofaImage.setPadding(Math.round(10*dm.density))
                        paramSofa.topMargin = Math.round(319*dm.density)
                        binding.sofaImage.layoutParams = paramSofa
                    }
                    2 -> {
                        binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa2))
                        binding.sofaImage.setPadding(Math.round(1*dm.density))
                        paramSofa.topMargin = Math.round(317*dm.density)
                        binding.sofaImage.layoutParams = paramSofa
                    }
                    3 -> {
                        binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa3))
                        binding.sofaImage.setPadding(Math.round(10*dm.density))
                        paramSofa.topMargin = Math.round(321*dm.density)
                        binding.sofaImage.layoutParams = paramSofa
                    }
                    4 -> {
                        binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa4))
                        binding.sofaImage.setPadding(Math.round(0*dm.density))
                        paramSofa.topMargin = Math.round(327*dm.density)
                        binding.sofaImage.layoutParams = paramSofa
                    }
                    5 -> {
                        binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa5))
                        binding.sofaImage.setPadding(Math.round(0*dm.density))
                        paramSofa.topMargin = Math.round(327*dm.density)
                        binding.sofaImage.layoutParams = paramSofa
                    }
                    6 -> {
                        binding.sofaImage.setImageDrawable(resources.getDrawable(R.drawable.sofa6))
                        binding.sofaImage.setPadding(Math.round(2*dm.density))
                        paramSofa.topMargin = Math.round(318*dm.density)
                        binding.sofaImage.layoutParams = paramSofa
                    }
                }

                val plantParam = ConstraintLayout.LayoutParams(
                    Math.round(53*dm.density), Math.round(50*dm.density)
                )
                plantParam.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                plantParam.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                plantParam.leftMargin = Math.round(234*dm.density)
                plantParam.height = Math.round(50 * dm.density)

                when (window) {
                    1 -> {
                        binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window1))

                    }
                    2 -> {
                        binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window2))

                    }
                    3 -> {
                        binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window3))

                    }
                    4 -> {
                        binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window4))

                    }
                    5 -> {
                        binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window5))

                    }
                    6 -> {
                        binding.windowImage.setImageDrawable(resources.getDrawable(R.drawable.window6))
                    }
                }

                when (wall) {
                    1 -> {
                        binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall1))
                    }
                    2 -> {
                        binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall2))
                    }
                    3 -> {
                        binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall3))
                    }
                    4 -> {
                        binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall4))
                    }
                    5 -> {
                        binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall5))
                    }
                    6 -> {
                        binding.wallImage.setImageDrawable(resources.getDrawable(R.drawable.wall6))
                    }
                }

                when (plant) {
                    1 -> {
                        plantParam.topMargin = Math.round(348 * dm.density)
                        binding.plantImage.layoutParams = plantParam
                        binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant1))
                    }
                    2 -> {
                        plantParam.topMargin = Math.round(348 * dm.density)
                        binding.plantImage.layoutParams = plantParam
                        binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant2))
                    }
                    3 -> {
                        plantParam.topMargin = Math.round(358 * dm.density)
                        binding.plantImage.layoutParams = plantParam
                        binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant3))
                    }
                    4 -> {
                        plantParam.topMargin = Math.round(348 * dm.density)
                        binding.plantImage.layoutParams = plantParam
                        binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant4))
                    }
                    5 -> {
                        plantParam.topMargin = Math.round(338 * dm.density)
                        plantParam.height = Math.round(60 * dm.density)
                        plantParam.marginStart = Math.round(244 * dm.density)
                        binding.plantImage.layoutParams = plantParam
                        binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant5))
                    }
                    6 -> {
                        plantParam.topMargin = Math.round(348 * dm.density)
                        plantParam.marginStart = Math.round(244 * dm.density)
                        binding.plantImage.layoutParams = plantParam
                        binding.plantImage.setImageDrawable(resources.getDrawable(R.drawable.plant6))
                    }
                }

                when(roof) {
                    1 -> {
                        binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof1))
                    }
                    2 -> {
                        binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof2))
                    }
                    3 -> {
                        binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof3))
                    }
                    4 -> {
                        binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof4))
                    }
                    5 -> {
                        binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof5))
                    }
                    6 -> {
                        binding.roofImage.setImageDrawable(resources.getDrawable(R.drawable.roof6))
                    }
                }

                when(wallpaper) {
                    1 -> {
                        binding.wallpaperImage.setImageDrawable(resources.getDrawable(R.drawable.wallpaper1))
                    }
                    2 -> {
                        binding.wallpaperImage.setImageDrawable(resources.getDrawable(R.drawable.wallpaper2))
                    }
                    3 -> {
                        binding.wallpaperImage.setImageDrawable(resources.getDrawable(R.drawable.wallpaper3))
                    }
                    4 -> {
                        binding.wallpaperImage.setImageDrawable(resources.getDrawable(R.drawable.wallpaper4))
                    }
                    5 -> {
                        binding.wallpaperImage.setImageDrawable(resources.getDrawable(R.drawable.wallpaper5))
                    }
                    6 -> {
                        binding.wallpaperImage.setImageDrawable(resources.getDrawable(R.drawable.wallpaper6))
                    }
                }


                val propParam = ConstraintLayout.LayoutParams(
                    Math.round(56*dm.density), Math.round(35*dm.density)
                )
                propParam.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                propParam.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                propParam.topMargin = Math.round(208 * dm.density)
                propParam.leftMargin = Math.round(118*dm.density)

                when (prop) {
                    1 -> {
                        propParam.height = Math.round(35 * dm.density)
                        binding.propImage.layoutParams = propParam
                        binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock))
                    }
                    2 -> {
                        propParam.height = Math.round(55 * dm.density)
                        binding.propImage.layoutParams = propParam
                        binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock2))
                    }
                    3 -> {
                        propParam.height = Math.round(55 * dm.density)
                        binding.propImage.layoutParams = propParam
                        binding.propImage.setImageDrawable(resources.getDrawable(R.drawable.clock3))
                    }
                }
                when (curtain) {
                    1 -> {
                        binding.curtainImage.setImageDrawable(resources.getDrawable(R.drawable.curtain1))
                    }
                    2 -> {
                        binding.curtainImage.setImageDrawable(resources.getDrawable(R.drawable.curtain2))
                    }
                    3 -> {
                        binding.curtainImage.setImageDrawable(resources.getDrawable(R.drawable.curtain3))
                    }
                }
            }
        }

        //TODO 그림마다 위치 재설정
        return view //inflater.inflate(R.layout.fragment_pet, container, false)
    }
}