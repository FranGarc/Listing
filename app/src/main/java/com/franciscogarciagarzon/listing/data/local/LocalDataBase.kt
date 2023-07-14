package com.franciscogarciagarzon.listing.data.local

import android.util.Log
import com.franciscogarciagarzon.listing.data.DataBase

class LocalDataBase : DataBase {
    override suspend fun doCallDatabase() {
        Log.i("LocalDataBase", "doCallDatabase called!")
    }
}