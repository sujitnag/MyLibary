package com.sujitnag.mylibary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM BookEntry")
    fun getAllBook():List<BookEntry>
    @Insert
    fun insertAll(book:BookEntry)
}