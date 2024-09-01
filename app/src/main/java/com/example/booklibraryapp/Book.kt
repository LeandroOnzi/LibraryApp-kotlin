package com.example.booklibraryapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Book(val id: Int, val title: String, val publisher: String, val genre: String, val userId: Int)

class BookRepository(private val dbHelper: DatabaseHelper) {

    // Adicionar um novo livro
    fun addBook(book: Book): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", book.title)
            put("publisher", book.publisher)
            put("genre", book.genre)
            put("userId", book.userId)
        }
        return db.insert("book", null, values)
    }

    // Obter todos os livros de um usuário
    fun getBooksByUserId(userId: Int): List<Book> {
        val books = mutableListOf<Book>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "book", null, "userId=?", arrayOf(userId.toString()), null, null, null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val publisher = cursor.getString(cursor.getColumnIndex("publisher"))
            val genre = cursor.getString(cursor.getColumnIndex("genre"))
            val userId = cursor.getInt(cursor.getColumnIndex("userId"))
            books.add(Book(id, title, publisher, genre, userId))
        }
        cursor.close()
        return books
    }


    // Obter um livro pelo ID
    fun getBookById(id: Int): Book? {
        val db = dbHelper.readableDatabase
        val cursor = db.query("book", null, "id=?", arrayOf(id.toString()), null, null, null)
        return if (cursor.moveToFirst()) {
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val publisher = cursor.getString(cursor.getColumnIndex("publisher"))
            val genre = cursor.getString(cursor.getColumnIndex("genre"))
            val userId = cursor.getInt(cursor.getColumnIndex("userId"))
            Book(id, title, publisher, genre, userId)
        } else {
            null
        }.also {
            cursor.close()
        }
    }

    // Atualizar um livro existente
    fun updateBook(book: Book): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", book.title)
            put("publisher", book.publisher)
            put("genre", book.genre)
            put("userId", book.userId)
        }
        return db.update("book", values, "id=?", arrayOf(book.id.toString()))
    }
}

