package com.example.booklibraryapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "library.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_USER_TABLE = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)"
        private const val CREATE_BOOK_TABLE = "CREATE TABLE book (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, publisher TEXT, genre TEXT, userId INTEGER, FOREIGN KEY(userId) REFERENCES user(id))"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
        db?.execSQL(CREATE_BOOK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS user")
        db?.execSQL("DROP TABLE IF EXISTS book")
        onCreate(db)
    }

    // Método para imprimir dados no Logcat
    fun printDatabaseContent() {
        val db = readableDatabase

        // Verificar usuários
        val userCursor = db.rawQuery("SELECT * FROM user", null)
        while (userCursor.moveToNext()) {
            val id = userCursor.getInt(userCursor.getColumnIndex("id"))
            val name = userCursor.getString(userCursor.getColumnIndex("name"))
            val email = userCursor.getString(userCursor.getColumnIndex("email"))
            Log.d("DatabaseContent", "User: id=$id, name=$name, email=$email")
        }
        userCursor.close()

        // Verificar livros
        val bookCursor = db.rawQuery("SELECT * FROM book", null)
        while (bookCursor.moveToNext()) {
            val id = bookCursor.getInt(bookCursor.getColumnIndex("id"))
            val title = bookCursor.getString(bookCursor.getColumnIndex("title"))
            val publisher = bookCursor.getString(bookCursor.getColumnIndex("publisher"))
            val genre = bookCursor.getString(bookCursor.getColumnIndex("genre"))
            val userId = bookCursor.getInt(bookCursor.getColumnIndex("userId"))
            Log.d("DatabaseContent", "Book: id=$id, title=$title, publisher=$publisher, genre=$genre, userId=$userId")
        }
        bookCursor.close()
    }
}

