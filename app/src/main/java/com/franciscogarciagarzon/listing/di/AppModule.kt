package com.franciscogarciagarzon.listing.di

import com.franciscogarciagarzon.listing.data.DataBase
import com.franciscogarciagarzon.listing.data.local.LocalDataBase
import com.franciscogarciagarzon.listing.data.local.repository.RepositoryImpl
import com.franciscogarciagarzon.listing.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //marks the object as a Dagger Hilt Module
@InstallIn(SingletonComponent::class) // tells DH to make these available as singleton during app life
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(): DataBase {
        return LocalDataBase()
    }

    @Provides
    @Singleton
    fun provideRepository(dataBase: DataBase): Repository {
        return RepositoryImpl(dataBase)
    }
}