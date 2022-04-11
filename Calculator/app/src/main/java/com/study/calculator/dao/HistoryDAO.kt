package com.study.calculator.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.study.calculator.model.History

@Dao
interface HistoryDAO {

    @Query("select * from history")
    fun getAll() : List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()

//    @Delete
//    fun delete(history: History)
//
//    @Query("select * from history where result LIKE :result")
//    fun findByResult(result: String) : List<History>

//    @Query("select * from history where result LIKE :result limit 1")
//    fun findByResult(result: String) : History

}