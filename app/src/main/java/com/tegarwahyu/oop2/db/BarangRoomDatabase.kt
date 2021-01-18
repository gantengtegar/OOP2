package com.tegarwahyu.oop2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tegarwahyu.oop2.model.Barang

@Database(entities = [Barang::class], version = 1, exportSchema = false)
abstract class BarangRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: BarangRoomDatabase? = null

        fun getDatabase(context: Context): BarangRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BarangRoomDatabase::class.java,
                    "barang_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate table if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getBarangDao() : BarangDao
}