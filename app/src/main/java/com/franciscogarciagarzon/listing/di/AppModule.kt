package com.franciscogarciagarzon.listing.di

import android.app.Application
import androidx.room.Room
import com.franciscogarciagarzon.listing.data.ListingRepository
import com.franciscogarciagarzon.listing.data.datasources.local.database.ListingDataBase
import com.franciscogarciagarzon.listing.domain.Repository
import com.franciscogarciagarzon.listing.domain.usecases.AddElementUseCase
import com.franciscogarciagarzon.listing.domain.usecases.GetElementsUseCase
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
    fun provideNoteDatabase(app: Application): ListingDataBase {
        return Room.databaseBuilder(
            app,
            ListingDataBase::class.java,
            ListingDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(dataBase: ListingDataBase): Repository {
        return ListingRepository(dataBase.listingDao)
    }

    @Provides
    @Singleton
    fun provideGetElementsUseCase(repository: Repository): GetElementsUseCase {
        return GetElementsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddElementUseCase(repository: Repository): AddElementUseCase {
        return AddElementUseCase(repository)
    }
}