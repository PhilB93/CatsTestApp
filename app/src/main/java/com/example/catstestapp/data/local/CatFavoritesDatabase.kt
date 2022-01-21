package com.example.catstestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catstestapp.data.local.entity.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 1
)
abstract class CatFavoritesDatabase: RoomDatabase() {

    abstract val dao: CatFavoritesDao
}