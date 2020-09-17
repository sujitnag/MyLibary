package com.sujitnag.mylibary

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val migration1To2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE BookEntry ADD COLUMN Prices INTEGER")
            }
        }


        //// database
        val db= Room.databaseBuilder(this,Mydatabase::class.java,"Bookdb").addMigrations(migration1To2).build()
        GlobalScope.launch(Dispatchers.Default) {
            db.bookDao().insertAll(BookEntry(8, "html",200))
            db.bookDao().insertAll(BookEntry(9, "prolog",400))
         //   db.bookDao().insertAll(BookEntry(7, "kotlin with adroid"))
        }
        fatchB.setOnClickListener {          view->
            GlobalScope.launch(Dispatchers.Default) {
                val books = db.bookDao().getAllBook()
                books.forEach {
                    txv.setText(txv.text.toString()+it.bookId.toString() + it.bookName + "\n")
                }
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}