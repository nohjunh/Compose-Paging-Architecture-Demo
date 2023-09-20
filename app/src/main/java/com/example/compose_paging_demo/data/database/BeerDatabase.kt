package com.example.compose_paging_demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compose_paging_demo.data.database.dao.BeerDao
import com.example.compose_paging_demo.data.database.model.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}
