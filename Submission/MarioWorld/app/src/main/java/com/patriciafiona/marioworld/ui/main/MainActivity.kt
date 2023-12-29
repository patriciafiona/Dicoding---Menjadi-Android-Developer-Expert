package com.patriciafiona.marioworld.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.patriciafiona.mario_world.core.data.Resource
import com.patriciafiona.mario_world.core.ui.CharacterAdapter
import com.patriciafiona.mario_world.core.ui.NewsAdapter
import com.patriciafiona.mario_world.core.utils.MediaPlayerManager
import com.patriciafiona.marioworld.databinding.ActivityMainBinding
import com.patriciafiona.marioworld.ui.detail.DetailCharacterActivity
import com.patriciafiona.marioworld.ui.profile.ProfileActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var bgSoundManager: MediaPlayerManager
    private lateinit var sharedPrefs: SharedPreferences
    private val mainViewModel: MainViewModel by viewModel()

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initView(){
        with(binding){
            btnSound.setOnClickListener {
                setSoundStatus(isChangeStatus = true)
            }

            btnProfile.setOnClickListener {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intent)
            }

            btnFavorite.setOnClickListener {
                val uri = Uri.parse("mario_world_favorite://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }

            rvNews.setHasFixedSize(true)
            showNewsRecyclerList()

            rvCharacters.setHasFixedSize(true)
            showCharactersRecyclerList()
        }
    }

    override fun onResume() {
        super.onResume()

        bgSoundManager = MediaPlayerManager(applicationContext)

        sharedPrefs = getSharedPreferences("com.patriciafiona.marioworld", MODE_PRIVATE)
        setSoundStatus()

        initView()
    }

    override fun onPause() {
        super.onPause()

        bgSoundManager.stopSound()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(com.patriciafiona.mario_world.core.R.string.double_press_exit), Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
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
            bgSoundManager.startSound(com.patriciafiona.mario_world.core.R.raw.bgm_overworld, true)
        }else{
            bgSoundManager.stopSound()
        }
    }

    private fun showNewsRecyclerList() {
        with(binding) {
            rvNews.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            val listNewsAdapter = NewsAdapter(mainViewModel.news)
            rvNews.adapter = listNewsAdapter

            listNewsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
                override fun onItemClicked(link: String) {
                    //Open link in the browser
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    startActivity(browserIntent)
                }
            })
        }
    }

    private fun showCharactersRecyclerList() {
        with(binding) {
            rvCharacters.layoutManager = LinearLayoutManager(this@MainActivity)
            val listCharactersAdapter = CharacterAdapter(this@MainActivity)
            listCharactersAdapter.onItemClick = { selectedData ->
                startActivity(DetailCharacterActivity.intent(this@MainActivity, selectedData))
            }

            mainViewModel.characters.observe(this@MainActivity) { characters ->
                if (characters != null) {
                    when (characters) {
                        is Resource.Loading -> {
                            binding.loadingCharacter.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.loadingCharacter.visibility = View.GONE
                            listCharactersAdapter.setData(characters.data)
                        }

                        is Resource.Error -> {
                            binding.loadingCharacter.visibility = View.GONE
                            Toast.makeText(
                                this@MainActivity,
                                "Can't get the characters data!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {}
                    }
                }
            }
            rvCharacters.adapter = listCharactersAdapter
        }
    }
}