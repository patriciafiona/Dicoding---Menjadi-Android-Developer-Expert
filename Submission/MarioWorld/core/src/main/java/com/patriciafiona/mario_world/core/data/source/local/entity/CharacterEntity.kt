package com.patriciafiona.mario_world.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "fullName")
    val fullName: String,

    @ColumnInfo(name = "dialog")
    val dialog: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "imageOpen")
    val imageOpen: String,

    @ColumnInfo(name = "imageClose")
    val imageClose: String,

    @ColumnInfo(name = "imageFull")
    val imageFull: String? = null,

    @ColumnInfo(name = "backgroundColor")
    val backgroundColor: String, //R, G, B

    @ColumnInfo(name = "ability")
    val ability: String,

    @ColumnInfo(name = "characterSound")
    val characterSound: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
