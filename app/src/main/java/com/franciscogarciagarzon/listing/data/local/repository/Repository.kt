package com.franciscogarciagarzon.listing.data.local.repository

interface Repository {

    suspend fun doDatabaseCall()

}