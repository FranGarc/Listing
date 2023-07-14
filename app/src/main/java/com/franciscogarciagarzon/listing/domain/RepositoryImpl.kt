package com.franciscogarciagarzon.listing.domain

import com.franciscogarciagarzon.listing.data.DataBase
import com.franciscogarciagarzon.listing.data.local.repository.Repository

class RepositoryImpl(private val db: DataBase): Repository {
    override suspend fun doDatabaseCall() {
        db.doCallDatabase()
    }
}