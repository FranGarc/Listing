package com.franciscogarciagarzon.listing.data

import com.franciscogarciagarzon.listing.data.datasources.local.database.ListingDao
import com.franciscogarciagarzon.listing.data.datasources.local.database.dto.ElementDto
import com.franciscogarciagarzon.listing.domain.Repository
import com.franciscogarciagarzon.listing.domain.entities.Element
import com.franciscogarciagarzon.listing.domain.entities.toDomainEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListingRepository @Inject constructor(
    private val db: ListingDao
) : Repository {
    override suspend fun addElement(element: Element) {
        db.insertElement(ElementDto(value = element.value))
    }

    override fun getElements(): Flow<List<Element>> {
        return db.getElements().map { databaseList ->
            databaseList.map { databaseListElement ->
                databaseListElement.toDomainEntity()
            }
        }
    }

}
