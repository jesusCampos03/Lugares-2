package com.example.lugares_trabajo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lugares_trabajo.model.Lugar

@Dao
interface LugarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLugar(lugar: Lugar)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateLugar(lugar: Lugar)

    @Delete
    suspend fun deleteLugar(lugar: Lugar)

    @Query("SELECT * FROM LUGAR")
    fun getLugares() : LiveData<List<Lugar>>
}