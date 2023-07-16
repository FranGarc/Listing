package com.franciscogarciagarzon.listing.domain.usecases

import com.franciscogarciagarzon.listing.domain.Repository
import com.franciscogarciagarzon.listing.domain.entities.Element
import com.franciscogarciagarzon.listing.domain.entities.InvalidElementException
import javax.inject.Inject

class AddElementUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(element: Element) {
        if (element.value.isBlank()) {
            throw InvalidElementException("The element can't be empty.")
        }
        repository.addElement(element)
    }
}