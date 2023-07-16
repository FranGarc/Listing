package com.franciscogarciagarzon.listing.data.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franciscogarciagarzon.listing.data.datasources.local.database.dto.ElementDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    // This is an observable query, we want it to emit each new value from any changes that may come to the table
    // This cannot be a suspend function
    @Query("SELECT * FROM elements")
    fun getElements(): Flow<List<ElementDto>>

    // This is an asynchronous one-shot query. They're to run once and, sometimes, grab a snapshot of the data at the time of execution
    // This _needs_ to be a suspend function
    @Insert(onConflict = OnConflictStrategy.REPLACE) // in case the insert has some conflict, replace the existing one with the new one
    suspend fun insertElement(note: ElementDto)

}