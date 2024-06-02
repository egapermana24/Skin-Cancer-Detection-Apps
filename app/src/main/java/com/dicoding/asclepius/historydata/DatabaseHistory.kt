package com.dicoding.asclepius.historydata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PredictionHistory::class], version = 2, exportSchema = false)
abstract class DatabaseHistory : RoomDatabase() {

    abstract fun predictionHistoryDao(): PredictionHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHistory? = null

        fun getDatabase(context: Context): DatabaseHistory {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHistory::class.java,
                    "history_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }


    }

}
