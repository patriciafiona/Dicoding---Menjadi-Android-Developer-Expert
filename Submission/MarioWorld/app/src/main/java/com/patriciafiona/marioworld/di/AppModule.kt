package com.patriciafiona.marioworld.di

import com.patriciafiona.mario_world.core.domain.usecase.MarioInteractor
import com.patriciafiona.mario_world.core.domain.usecase.MarioUseCase
import com.patriciafiona.marioworld.ui.detail.DetailCharacterViewModel
import com.patriciafiona.marioworld.ui.favorite.FavoriteViewModel
import com.patriciafiona.marioworld.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MarioUseCase> { MarioInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailCharacterViewModel(get()) }
}