package com.study.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.study.calculator.dao.HistoryDAO
import com.study.calculator.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDAO
}