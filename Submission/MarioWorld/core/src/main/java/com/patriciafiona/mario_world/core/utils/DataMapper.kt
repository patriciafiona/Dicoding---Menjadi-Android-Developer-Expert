package com.patriciafiona.mario_world.core.utils

import com.patriciafiona.mario_world.core.data.source.local.entity.CharacterEntity
import com.patriciafiona.mario_world.core.data.source.remote.response.CharacterResponse
import com.patriciafiona.mario_world.core.domain.model.Character

object DataMapper {
    fun mapResponsesToEntities(input: List<CharacterResponse>): List<CharacterEntity> {
        val characterList = ArrayList<CharacterEntity>()
        input.map {
            val character = CharacterEntity(
                name = it.name,
                fullName = it.fullName,
                dialog = it.dialog,
                species = it.species,
                description = it.description,
                imageOpen = it.imageOpen,
                imageClose = it.imageClose,
                imageFull = it.imageFull,
                backgroundColor = it.backgroundColor, //R, G, B
                ability = it.ability,
                characterSound = it.characterSound,
                isFavorite = false
            )
            characterList.add(character)
        }
        return characterList
    }

    fun mapEntitiesToDomain(input: List<CharacterEntity>): List<Character> =
        input.map {
            Character(
                name = it.name,
                fullName = it.fullName,
                dialog = it.dialog,
                species = it.species,
                description = it.description,
                imageOpen = it.imageOpen,
                imageClose = it.imageClose,
                imageFull = it.imageFull,
                backgroundColor = it.backgroundColor, //R, G, B
                ability = it.ability,
                characterSound = it.characterSound,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(it: Character) = CharacterEntity(
        name = it.name,
        fullName = it.fullName,
        dialog = it.dialog,
        species = it.species,
        description = it.description,
        imageOpen = it.imageOpen,
        imageClose = it.imageClose,
        imageFull = it.imageFull,
        backgroundColor = it.backgroundColor, //R, G, B
        ability = it.ability,
        characterSound = it.characterSound,
        isFavorite = it.isFavorite
    )
}