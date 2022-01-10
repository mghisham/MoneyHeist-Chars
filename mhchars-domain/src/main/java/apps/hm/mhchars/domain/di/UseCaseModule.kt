package apps.hm.mhchars.domain.di

import apps.hm.mhchars.domain.usecase.CharactersUseCase
import apps.hm.mhchars.domain.usecase.CharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    internal abstract fun bindCharacterUseCase(useCaseImpl: CharactersUseCaseImpl): CharactersUseCase
}