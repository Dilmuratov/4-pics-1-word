package com.example.iq4pics1word

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.iq4pics1word.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        animationCoin()

        btnAddCoin()
    }

    private fun animationCoin() {
        binding.ivCoin.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_animation))
        binding.ivAdd.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation))
    }

    /**
     * Add coin aynasi
     */
    fun btnAddCoin(){
        binding.cvAddcoin.setOnClickListener {
            binding.clWindowAd.visibility = View.VISIBLE

            binding.ivClose.setOnClickListener {
                binding.clWindowAd.visibility = View.GONE
            }
        }
    }
}