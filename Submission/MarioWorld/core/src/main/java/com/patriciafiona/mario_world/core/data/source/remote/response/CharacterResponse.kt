package com.patriciafiona.mario_world.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("fullName")
    val fullName: String,

    @field:SerializedName("dialog")
    val dialog: String,

    @field:SerializedName("species")
    val species: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("imageOpen")
    val imageOpen: String,

    @field:SerializedName("imageClose")
    val imageClose: String,

    @field:SerializedName("imageFull")
    val imageFull: String? = null,

    @field:SerializedName("backgroundColor")
    val backgroundColor: String, //R, G, B

    @field:SerializedName("ability")
    val ability: String,

    @field:SerializedName("characterSound")
    val characterSound: String,
)

