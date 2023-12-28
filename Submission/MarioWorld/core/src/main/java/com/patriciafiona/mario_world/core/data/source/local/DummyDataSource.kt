package com.patriciafiona.mario_world.core.data.source.local

import com.patriciafiona.mario_world.core.R
import com.patriciafiona.mario_world.core.domain.model.News

object DummyDataSource {
    fun getAllNews(): ArrayList<News> {
        return arrayListOf(
            News(
                title = "Discover the all-new course Piranha Plant Cove with the Exploration Tour",
                headline = "Discover a brand-new course in the Mario Kart™ Tour game with the Exploration Tour, the latest limited-time event in the Mario Kart Tour game, happening now.",
                link = "https://mario.nintendo.com/news/mobilenews-discover-the-all-new-course-piranha-plant-cove-with-the-exploration-tour/",
                image = R.drawable.news_01,
                date = "02.10.2023"
            ),
            News(
                title = "Wii Rainbow Road blasts off in the Space Tour",
                headline = "Three…two…one…liftoff! Launch into galactic kart-racing fun with the Space Tour, the latest limited-time event in the Mario Kart Tour game, happening now.",
                link = "https://mario.nintendo.com/news/mobilenews-wii-rainbow-road-blasts-off-in-the-space-tour/",
                image = R.drawable.news_02,
                date = "01.12.2023"
            ),
            News(
                title = "Happy New Year’s Tour!",
                headline = "Put 2022 in your rear-view mirror and race into 2023 with the New Year’s Tour, the latest limited-time event in the Mario Kart Tour game, happening now!",
                link = "https://mario.nintendo.com/news/happy-new-years-tour/",
                image = R.drawable.news_03,
                date = "12.29.2022"
            )
        )
    }

    val characterSoundLibrary = mapOf(
        "sound_mario_0" to R.raw.sound_mario_0,
        "sound_mario_1" to R.raw.sound_mario_1,
        "sound_mario_2" to R.raw.sound_mario_2,

        "sound_luigi_0" to R.raw.sound_luigi_0,
        "sound_luigi_1" to R.raw.sound_luigi_1,
        "sound_luigi_2" to R.raw.sound_luigi_2,

        "sound_peach_0" to R.raw.sound_peach_0,
        "sound_peach_1" to R.raw.sound_peach_1,
        "sound_peach_2" to R.raw.sound_peach_2,

        "sound_toad_0" to R.raw.sound_toad_0,
        "sound_toad_1" to R.raw.sound_toad_1,
        "sound_toad_2" to R.raw.sound_toad_2,

        "sound_bowser_0" to R.raw.sound_bowser_0,
        "sound_bowser_1" to R.raw.sound_bowser_1,
        "sound_bowser_2" to R.raw.sound_bowser_2,

        "sound_yoshi_0" to R.raw.sound_yoshi_0,
        "sound_yoshi_1" to R.raw.sound_yoshi_1,
        "sound_yoshi_2" to R.raw.sound_yoshi_2,

        "sound_daisy_0" to R.raw.sound_daisy_0,
        "sound_daisy_1" to R.raw.sound_daisy_1,
        "sound_daisy_2" to R.raw.sound_daisy_2,

        "sound_wario_0" to R.raw.sound_wario_0,
        "sound_wario_1" to R.raw.sound_wario_1,
        "sound_wario_2" to R.raw.sound_wario_2,

        "sound_waluigi_0" to R.raw.sound_waluigi_0,
        "sound_waluigi_1" to R.raw.sound_waluigi_1,
        "sound_waluigi_2" to R.raw.sound_waluigi_2,

        "sound_rosalina_0" to R.raw.sound_rosalina_0,
        "sound_rosalina_1" to R.raw.sound_rosalina_1,
        "sound_rosalina_2" to R.raw.sound_rosalina_2,

        "sound_bowser_jr_0" to R.raw.sound_bowser_jr_0,
        "sound_bowser_jr_1" to R.raw.sound_bowser_jr_1,
        "sound_bowser_jr_2" to R.raw.sound_bowser_jr_2,

        "sound_boo_0" to R.raw.sound_boo_0,
        "sound_boo_1" to R.raw.sound_boo_1,
        "sound_boo_2" to R.raw.sound_boo_2,

        "sound_donkey_kong_0" to R.raw.sound_donkey_kong_0,
        "sound_donkey_kong_1" to R.raw.sound_donkey_kong_1,
        "sound_donkey_kong_2" to R.raw.sound_donkey_kong_2,

        "sound_diddy_kong_0" to R.raw.sound_diddy_kong_0,
        "sound_diddy_kong_1" to R.raw.sound_diddy_kong_1,
        "sound_diddy_kong_2" to R.raw.sound_diddy_kong_2,
    )
}