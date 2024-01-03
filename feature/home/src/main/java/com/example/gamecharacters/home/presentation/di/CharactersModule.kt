package com.example.gamecharacters.home.presentation.di


import com.example.gamecharacters.home.presentation.CharactersNavigationFactory
import com.example.gamecharacters.home.presentation.characterDetail.CharacterDetailUiState
import com.example.gamecharacters.home.presentation.characterList.CharactersUiState
import com.example.gamecharacters.navigation.NavigationFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object CharactersViewModelModule {

    @Provides
    fun provideInitialCharactersUiState(): CharactersUiState = CharactersUiState()
}

@Module
@InstallIn(ViewModelComponent::class)
object CharacterDetailViewModelModule {

    @Provides
    fun provideInitialCharacterDetailUiState(): CharacterDetailUiState = CharacterDetailUiState()
}

@Module
@InstallIn(SingletonComponent::class)
interface CharactersSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindCharactersNavigationFactory(factory: CharactersNavigationFactory): NavigationFactory
}
