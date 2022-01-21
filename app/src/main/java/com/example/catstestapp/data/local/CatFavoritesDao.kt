package com.example.catstestapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catstestapp.data.local.entity.CatEntity

@Dao
interface CatFavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatEntity(catEntity: CatEntity)

    @Query("DELETE FROM catentity  WHERE id IN(:id)")
    suspend fun deleteCatEntity(id: String)

    @Query("SELECT * FROM catentity")
    suspend fun getCatEntity(): List<CatEntity>
}