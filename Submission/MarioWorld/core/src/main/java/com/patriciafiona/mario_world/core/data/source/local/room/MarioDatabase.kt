package com.patriciafiona.mario_world.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patriciafiona.mario_world.core.data.source.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class MarioDatabase : RoomDatabase() {

    abstract fun marioDao(): MarioDao

}