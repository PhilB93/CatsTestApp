package com.example.catstestapp.data.local

import androidx.room.*
import com.example.catstestapp.data.local.entity.CatEntity

@Dao
interface CatFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatEntity(catEntity: CatEntity)

    @Delete
    suspend fun delete(catEntity: CatEntity)

    @Query("SELECT * FROM catentity")
    suspend fun getCatEntity(): List<CatEntity>
}