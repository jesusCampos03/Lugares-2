package com.example.lugares_trabajo.repository

import androidx.lifecycle.LiveData
import com.example.lugares_trabajo.data.LugarDao
import com.example.lugares_trabajo.model.Lugar
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

class LugarRepository(private val lugarDao: LugarDao) {
    suspend fun saveLugar(lugar: Lugar) {
        if (lugar.id==0) {  //Es un lugar nuevo
            lugarDao.addLugar(lugar)
        } else {  //El lugar existe... entonces se actualiza...
            lugarDao.updateLugar(lugar)
        }
    }

    suspend fun deleteLugar(lugar: Lugar) {
        lugarDao.deleteLugar(lugar)
    }

    val getLugares : LiveData<List<Lugar>> = lugarDao.getLugares()
}