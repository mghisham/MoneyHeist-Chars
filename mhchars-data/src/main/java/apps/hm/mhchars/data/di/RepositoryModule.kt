package apps.hm.mhchars.data.di

import apps.hm.mhchars.data.repository.CharsRepositoryImpl
import apps.hm.mhchars.domain.repository.CharsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCharsRepository(repository: CharsRepositoryImpl): CharsRepository
}