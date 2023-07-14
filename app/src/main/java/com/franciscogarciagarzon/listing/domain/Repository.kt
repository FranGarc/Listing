package com.franciscogarciagarzon.listing.domain

interface Repository {
    suspend fun doDatabaseCall()
}