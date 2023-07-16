package com.franciscogarciagarzon.listing.ui

import com.franciscogarciagarzon.listing.domain.entities.Element

sealed interface ListingEvent {
    data class ErrorEvent(val errorMessage: String) : ListingEvent
    data class DeleteElement(val element: Element) : ListingEvent
    data class EditElement(val element: Element) : ListingEvent
    data class AddElement(val value: String) : ListingEvent
}