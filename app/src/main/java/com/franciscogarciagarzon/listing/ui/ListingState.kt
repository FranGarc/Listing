package com.franciscogarciagarzon.listing.ui

import com.franciscogarciagarzon.listing.domain.entities.Element

sealed interface ListingState {
    data class ListState(
        val elements: List<Element> = emptyList(),
    ) : ListingState

    data class ErrorState(val errorMessage: String = "")
}
