package com.patriciafiona.mario_world.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.patriciafiona.mario_world.core.data.source.remote.response.CharacterResponse

data class ListCharacterResponse(

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("source")
    val source: String,

    @field:SerializedName("results")
    val results: List<CharacterResponse>
)