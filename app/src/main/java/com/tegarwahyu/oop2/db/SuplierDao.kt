package com.tegarwahyu.oop2.db

import androidx.room.*
import com.tegarwahyu.oop2.model.Suplier

@Dao
interface SuplierDao {

    @Insert
    fun insert(suplier: Suplier)

    @Update
    fun update(suplier: Suplier)

    @Delete
    fun delete(suplier: Suplier)

    @Query("SELECT * FROM suplier")
    fun getAll() : List<Suplier>

    @Query("SELECT * FROM suplier WHERE id = :id")
    fun getById(id: Int) : List<Suplier>
}