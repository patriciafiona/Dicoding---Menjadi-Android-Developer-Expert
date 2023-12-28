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
    val characterSound: String,
    val isFavorite: Boolean
) : Parcelable{
    //Convert Abilities value & Set into variable
    @IgnoredOnParcel
    private val abilities = ability.split(",")

    @IgnoredOnParcel
    val acceleration: Double = abilities[0].trim().toDouble()
    @IgnoredOnParcel
    val maxSpeed: Double = abilities[1].trim().toDouble()
    @IgnoredOnParcel
    val technique: Double = abilities[2].trim().toDouble()
    @IgnoredOnParcel
    val power: Double = abilities[3].trim().toDouble()
    @IgnoredOnParcel
    val stamina: Double = abilities[4].trim().toDouble()

    //Convert Background Colors value & Set into variable
    @IgnoredOnParcel
    private val bgColors = backgroundColor.split(",")

    @IgnoredOnParcel
    val bgColorR: Int = bgColors[0].trim().toInt()
    @IgnoredOnParcel
    val bgColorG: Int = bgColors[1].trim().toInt()
    @IgnoredOnParcel
    val bgColorB: Int = bgColors[2].trim().toInt()

}