package com.franciscogarciagarzon.listing.domain.entities

import com.franciscogarciagarzon.listing.data.datasources.local.database.dto.ElementDto

// this is the class the presenter module will work with. In this case is identical to the dto, but at times it will need less or even more than the datasource provides
data class Element(
    val id: Int? = null,
    val value: String
)

// to avoid dependencies between presenter and data (clean architecture) we set this transformation extension function
fun ElementDto.toDomainEntity(): Element = Element(this.id, this.value)

class InvalidElementException(message: String) : Exception(message)
