package com.patriciafiona.mario_world.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val name: String,
    val fullName: String,
    val dialog: String,
    val species: String,
    val description: String,
    val imageOpen: String,
    val imageClose: String,
    val imageFull: String? = null,
    val backgroundColor: String, //R, G, B
    val ability: String,
    val isFavorite: Boolean
) : Parcelable{
    @IgnoredOnParcel
    private val abilities = ability.split(",")

    @IgnoredOnParcel
    val acceleration: Double = abilities[0].toDouble()
    @IgnoredOnParcel
    val maxSpeed: Double = abilities[1].toDouble()
    @IgnoredOnParcel
    val technique: Double = abilities[2].toDouble()
    @IgnoredOnParcel
    val power: Double = abilities[3].toDouble()
    @IgnoredOnParcel
    val stamina: Double = abilities[4].toDouble()
}