package com.patriciafiona.marioworld.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        with(binding){
            btnBack.setOnClickListener { onBackPressed() }
        }
    }
}