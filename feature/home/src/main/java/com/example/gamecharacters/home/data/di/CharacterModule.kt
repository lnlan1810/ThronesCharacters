package com.example.gamecharacters.home.data.di

import com.example.gamecharacters.home.data.remote.api.ThronesApi
import com.example.gamecharacters.home.data.repository.CharacterRepositoryImpl
import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.home.domain.usecase.GetCharacterUseCase
import com.example.gamecharacters.home.domain.usecase.GetCharacterUseCaseImpl
import com.example.gamecharacters.home.domain.usecase.GetCharactersUseCase
import com.example.gamecharacters.home.domain.usecase.RefreshCharactersUseCase
import com.example.gamecharacters.home.domain.usecase.getCharacters
import com.example.gamecharacters.home.domain.usecase.refreshCharacters
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [CharacterModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideThronesApi(
        retrofit: Retrofit
    ): ThronesApi {
        return retrofit.create(ThronesApi::class.java)
    }

    @Provides
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase {
            getCharacters(characterRepository)
        }
    }

    @Provides
    fun provideRefreshCharactersUseCase(
        characterRepository: CharacterRepository
    ): RefreshCharactersUseCase {
        return RefreshCharactersUseCase {
            refreshCharacters(characterRepository)
        }
    }

    @Provides
    fun provideGetCharacterUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterUseCase {
        return GetCharacterUseCaseImpl(characterRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
    }
}
