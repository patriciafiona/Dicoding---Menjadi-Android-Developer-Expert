package com.patriciafiona.mario_world.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.patriciafiona.mario_world.core.data.source.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarioDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters where isFavorite = 1")
    fun getFavoriteCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: List<CharacterEntity>)

    @Update
    fun updateFavoriteCharacter(character: CharacterEntity)
}
