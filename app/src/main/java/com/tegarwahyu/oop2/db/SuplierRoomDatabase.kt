package com.tegarwahyu.oop2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tegarwahyu.oop2.model.Suplier

//Database annotation to specify the entities and set version
@Database(entities = [Suplier::class], version = 1, exportSchema = false)
abstract class SuplierRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: SuplierRoomDatabase? = null

        fun getDatabase(context: Context): SuplierRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuplierRoomDatabase::class.java,
                    "suplier_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate table if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getSuplierDao() : SuplierDao
}
