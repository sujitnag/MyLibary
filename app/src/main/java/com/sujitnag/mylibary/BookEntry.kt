package com.sujitnag.mylibary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BookEntry constructor(bookId:Int, bookName:String,prices:Int){
    @PrimaryKey
    var bookId:Int=bookId
    @ColumnInfo(name="BookName")
    var bookName:String?=bookName
    @ColumnInfo(name="Price")
    var prices:Int=prices

}