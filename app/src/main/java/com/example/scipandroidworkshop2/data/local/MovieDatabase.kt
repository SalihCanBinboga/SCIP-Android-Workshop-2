package com.example.scipandroidworkshop2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scipandroidworkshop2.data.models.Results


@Database(entities = [Results::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}
