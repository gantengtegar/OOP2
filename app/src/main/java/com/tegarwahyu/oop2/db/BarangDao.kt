package com.tegarwahyu.oop2.db


import androidx.room.*
import com.tegarwahyu.oop2.model.Barang

@Dao
interface BarangDao {

    @Insert
    fun insert(barang: Barang)

    @Update
    fun update(barang: Barang)

    @Delete
    fun delete(barang: Barang)

    @Query("SELECT * FROM barang")
    fun getAll() : List<Barang>

    @Query("SELECT * FROM barang WHERE id = :id")
    fun getById(id: Int) : List<Barang>
}