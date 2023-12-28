package com.patriciafiona.mario_world.core.data

import com.patriciafiona.mario_world.core.data.source.local.LocalDataSource
import com.patriciafiona.mario_world.core.data.source.remote.RemoteDataSource
import com.patriciafiona.mario_world.core.data.source.remote.network.ApiResponse
import com.patriciafiona.mario_world.core.data.source.remote.response.CharacterResponse
import com.patriciafiona.mario_world.core.domain.model.Character
import com.patriciafiona.mario_world.core.domain.repository.IMarioRepository
import com.patriciafiona.mario_world.core.utils.AppExecutors
import com.patriciafiona.mario_world.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MarioRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMarioRepository {

    override fun getAllCharacters(): Flow<Resource<List<Character>>> =
        object : NetworkBoundResource<List<Character>, List<CharacterResponse>>() {
            override fun loadFromDB(): Flow<List<Character>> {
                return localDataSource.getAllCharacters().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Character>?): Boolean =
                data == null || data.isEmpty()
//                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<CharacterResponse>>> =
                remoteDataSource.getAllCharacters()

            override suspend fun saveCallResult(data: List<CharacterResponse>) {
                val characterList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertCharacter(characterList)
            }
        }.asFlow()

    override fun getFavoriteCharacters(): Flow<List<Character>> {
        return localDataSource.getFavoriteCharacter().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteCharacter(character: Character, state: Boolean) {
        val characterEntity = DataMapper.mapDomainToEntity(character)
        appExecutors.diskIO().execute { localDataSource.setFavoriteCharacter(characterEntity, state) }
    }
}

