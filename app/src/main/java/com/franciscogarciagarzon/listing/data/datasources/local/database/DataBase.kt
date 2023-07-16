package com.franciscogarciagarzon.listing.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franciscogarciagarzon.listing.data.datasources.local.database.dto.ElementDto

@Database(
    entities = [ElementDto::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract val listingDao: ListingDao

    companion object {
        const val DATABASE_NAME = "listing_db"
    }
}