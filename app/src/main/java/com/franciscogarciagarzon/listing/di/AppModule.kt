package com.franciscogarciagarzon.listing.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module //marks the object as a Dagger Hilt Module
@InstallIn(SingletonComponent::class) // tells DH to make these available as singleton during app life
object AppModule {

//    @Provides
//    @Singleton
//    fun provideDataBase(): DataBase {
//        return LocalDataBase()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRepository(dataBase: DataBase): Repository {
//        return RepositoryImpl(dataBase)
//    }
}