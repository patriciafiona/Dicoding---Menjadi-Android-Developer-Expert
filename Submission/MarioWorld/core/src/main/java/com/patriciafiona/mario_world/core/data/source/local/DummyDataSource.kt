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
}