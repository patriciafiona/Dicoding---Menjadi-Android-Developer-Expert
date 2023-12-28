package com.patriciafiona.mario_world.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListCharacterResponse(

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("source")
    val source: String,

    @field:SerializedName("results")
    val results: List<CharacterResponse>
)