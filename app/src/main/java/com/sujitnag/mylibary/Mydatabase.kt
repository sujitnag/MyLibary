package com.sujitnag.mylibary

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BookEntry::class),version = 2)
abstract  class Mydatabase :RoomDatabase(){
abstract  fun bookDao():BookDao
}
