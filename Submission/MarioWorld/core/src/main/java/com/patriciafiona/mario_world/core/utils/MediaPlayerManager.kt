package com.patriciafiona.mario_world.core.utils

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class MediaPlayerManager(private val mContext: Context) {
    private var mMediaPlayer: MediaPlayer? = null
    private var isReleased: Boolean = false

    fun startSound(sound: Int, isLooping: Boolean = false){
        mMediaPlayer = MediaPlayer.create(mContext, sound)
        mMediaPlayer!!.isLooping = isLooping
        mMediaPlayer!!.start()
    }

    fun stopSound(){
        try {
            if (mMediaPlayer != null && !isReleased) {
                if(mMediaPlayer!!.isPlaying) {
                    mMediaPlayer!!.stop()
                    mMediaPlayer!!.release()
                    mMediaPlayer = null
                }
            }
        }catch (e: Exception){
            Log.e("MediaPlayerManager", e.stackTraceToString())
        }
    }
}