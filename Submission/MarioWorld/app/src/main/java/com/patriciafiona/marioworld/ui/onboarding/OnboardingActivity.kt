package com.patriciafiona.marioworld.ui.onboarding

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncorti.slidetoact.SlideToActView
import com.patriciafiona.mario_world.core.utils.MediaPlayerManager
import com.patriciafiona.marioworld.databinding.ActivityOnboardingBinding
import com.patriciafiona.marioworld.ui.main.MainActivity
import kotlinx.coroutines.runBlocking

class OnboardingActivity : AppCompatActivity(), SlideToActView.OnSlideToActAnimationEventListener {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var bgSoundManager: MediaPlayerManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("com.patriciafiona.marioworld", MODE_PRIVATE)
        if(!sharedPrefs.contains("isMute")){
            //default sound is not mute
            val ed = sharedPrefs.edit()
            ed.putBoolean("isMute", false)
            ed.apply()
        }
    }

    override fun onResume() {
        super.onResume()
        bgSoundManager = MediaPlayerManager(applicationContext)
        setSoundStatus()

        initView()
    }

    override fun onPause() {
        super.onPause()
        bgSoundManager.stopSound()
    }

    private fun initView(){
        with(binding){
            btnSlider.onSlideToActAnimationEventListener = this@OnboardingActivity

            btnSound.setOnClickListener {
                setSoundStatus(isChangeStatus = true)
            }

            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    marioTitle.layoutParams.width = 800
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    marioTitle.layoutParams.width = 600
                }

                Configuration.ORIENTATION_SQUARE -> {
                    marioTitle.layoutParams.width = 600
                }

                Configuration.ORIENTATION_UNDEFINED -> {
                    marioTitle.layoutParams.width = 800
                }
            }
        }
    }

    private fun setSoundStatus(isChangeStatus: Boolean = false){
        var currentStatus = sharedPrefs.getBoolean("isMute", false)
        if(isChangeStatus) {
            sharedPrefs.edit()
                .putBoolean("isMute", !currentStatus)
                .apply()
        }

        currentStatus = sharedPrefs.getBoolean("isMute", false)
        if(currentStatus){
            binding.btnSound.setImageResource(com.patriciafiona.mario_world.core.R.drawable.ic_volume_off)
        }else{
            binding.btnSound.setImageResource(com.patriciafiona.mario_world.core.R.drawable.ic_volume_up)
        }

        isPlayBgSound()
    }

    private fun isPlayBgSound(){
        val isMute = sharedPrefs.getBoolean("isMute", false)

        if(!isMute) {
            //set bg sound
            bgSoundManager.startSound(com.patriciafiona.mario_world.core.R.raw.bgm_opening, true)
        }else{
            bgSoundManager.stopSound()
        }
    }

    override fun onSlideCompleteAnimationEnded(view: SlideToActView) {
        val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSlideCompleteAnimationStarted(view: SlideToActView, threshold: Float) {
        runBlocking {
            bgSoundManager.stopSound()

            val mpManager = MediaPlayerManager(applicationContext)
            mpManager.startSound(com.patriciafiona.mario_world.core.R.raw.continue_sound, false)
        }
    }

    override fun onSlideResetAnimationEnded(view: SlideToActView) { }

    override fun onSlideResetAnimationStarted(view: SlideToActView) { }
}