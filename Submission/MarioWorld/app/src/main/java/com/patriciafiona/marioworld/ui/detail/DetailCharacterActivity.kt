package com.patriciafiona.marioworld.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.patriciafiona.mario_world.core.utils.MediaPlayerManager
import com.patriciafiona.marioworld.databinding.ActivityDetailCharacterBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import com.patriciafiona.mario_world.core.domain.model.Character
import com.patriciafiona.mario_world.core.utils.Utils.imageURL
import com.patriciafiona.marioworld.R
import org.koin.android.viewmodel.ext.android.viewModel

class DetailCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCharacterBinding

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var bgSoundManager: MediaPlayerManager

    private val detailCharacterViewModel: DetailCharacterViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("com.patriciafiona.marioworld", MODE_PRIVATE)
        bgSoundManager = MediaPlayerManager(applicationContext)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){
        with(binding){
            val data: Character? = intent.extras!!.getParcelable(EXTRA_DATA)
            if(data != null) {
                val color = Color.rgb(data.bgColorR, data.bgColorG, data.bgColorB)

                btnBack.setOnClickListener {
                    onBackPressed()
                }

                btnSound.setOnClickListener {
                    setSoundStatus(isChangeStatus = true)
                }

                actionShare.setOnClickListener {
                    val i = Intent(Intent.ACTION_SEND)
                    i.type = "text/plain"
                    i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL")
                    i.putExtra(Intent.EXTRA_TEXT, "https://mario.nintendo.com/characters/")
                    startActivity(Intent.createChooser(i, "Share Official Website URL"))
                }

                var statusFavorite = data.isFavorite
                Log.e("IsFavorite", "status: $statusFavorite")
                setStatusFavorite(statusFavorite)
                actionFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailCharacterViewModel.setFavoriteCharacter(data, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = color

                Glide.with(this@DetailCharacterActivity)
                    .load("$imageURL${data.imageFull ?: data.imageOpen}")
                    .placeholder(com.patriciafiona.mario_world.core.R.drawable.ic_image)
                    .skipMemoryCache(true)
                    .into(ivPhoto)

                val characterSoundManager = MediaPlayerManager(applicationContext)
                btnSound1.setColor(color)
//                btnSound1.setOnClickListener {
//                    characterSoundManager.startSound(data.characterSound[0])
//                }

                btnSound2.setColor(color)
//                btnSound2.setOnClickListener {
//                    characterSoundManager.startSound(data.characterSound[1])
//                }

                btnSound3.setColor(color)
//                btnSound3.setOnClickListener {
//                    characterSoundManager.startSound(data.characterSound[2])
//                }

                accelerationRating.rating = (data.acceleration / 2.0).toFloat()
                maxSpeedRating.rating = (data.maxSpeed / 2.0).toFloat()
                techniqueRating.rating = (data.technique / 2.0).toFloat()
                powerRating.rating = (data.power / 2.0).toFloat()
                staminaRating.rating = (data.stamina / 2.0).toFloat()

                val gd = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
                        color,
                        Color.BLACK
                    )
                )
                gd.cornerRadius = 0f
                container.background = gd

                tvName.text = data.name
                tvFullNameAndSpecies.text = "${data.fullName} - ${data.species}"
                tvDescription.text = data.description
                tvDialog.text = data.dialog
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.actionFavorite.setImageResource(
            if (statusFavorite){
                com.patriciafiona.mario_world.core.R.drawable.ic_favorite_red
            } else {
                com.patriciafiona.mario_world.core.R.drawable.ic_favorite_border_white
            }
        )
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