package com.patriciafiona.mario_world.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID


@Parcelize
data class News(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val headline: String,
    val link: String,
    val date: String,
    val image: Int
): Parcelable
