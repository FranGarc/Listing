package com.franciscogarciagarzon.listing.data.datasources.local.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elements")
data class ElementDto(
    @PrimaryKey val id: Int? = null,
    val value: String
)