package com.patriciafiona.marioworld.ui.favorite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.patriciafiona.mario_world.core.ui.CharacterAdapter
import com.patriciafiona.mario_world.core.utils.MediaPlayerManager
import com.patriciafiona.marioworld.databinding.ActivityFavoriteBinding
import com.patriciafiona.marioworld.ui.detail.DetailCharacterActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFavoriteBinding

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var bgSoundManager: MediaPlayerManager

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    companion object{
        fun intent(context: Context): Intent{
            return Intent(context, FavoriteActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("com.patriciafiona.marioworld", MODE_PRIVATE)
        bgSoundManager = MediaPlayerManager(applicationContext)

        initView()
    }

    private fun initView(){
        with(binding){
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnSound.setOnClickListener {
                setSoundStatus(isChangeStatus = true)
            }

            val characterAdapter = CharacterAdapter(this@FavoriteActivity)
            characterAdapter.onItemClick = { selectedData ->
                startActivity(DetailCharacterActivity.intent(this@FavoriteActivity, selectedData))
            }

            favoriteViewModel.favoriteCharacter.observe(this@FavoriteActivity) { data ->
                characterAdapter.setData(data)
                emptyAnimation.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
                rvFavoriteCharacters.visibility = if (data.isNotEmpty()) View.VISIBLE else View.GONE
            }

            with(binding.rvFavoriteCharacters) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = characterAdapter
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
            bgSoundManager.startSound(com.patriciafiona.mario_world.core.R.raw.bgm_super_mario_bos, true)
        }else{
            bgSoundManager.stopSound()
        }
    }

    override fun onResume() {
        super.onResume()

        initView()

        runBlocking {
            delay(1500)

            setSoundStatus()
        }
    }

    override fun onPause() {
        super.onPause()

        bgSoundManager.stopSound()
    }
}