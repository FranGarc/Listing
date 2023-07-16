package com.franciscogarciagarzon.listing.domain.usecases

import com.franciscogarciagarzon.listing.domain.Repository
import com.franciscogarciagarzon.listing.domain.entities.Element
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetElementsUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<List<Element>> {
        return repository.getElements()
    }
}