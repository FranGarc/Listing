package com.franciscogarciagarzon.listing.data.local.repository

import com.franciscogarciagarzon.listing.data.DataBase
import com.franciscogarciagarzon.listing.domain.Repository
import javax.inject.Inject

class RepositoryImpl
    @Inject constructor(private val db: DataBase): Repository {
    override suspend fun doDatabaseCall() {
        db.doCallDatabase()
    }
}