package com.franciscogarciagarzon.listing.domain

import com.franciscogarciagarzon.listing.domain.entities.Element
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun addElement(element: Element)

    fun getElements(): Flow<List<Element>>
}