package com.example.booklibraryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookDetailActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var bookRepository: BookRepository
    private var bookId: Int = -1
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        dbHelper = DatabaseHelper(this)
        bookRepository = BookRepository(dbHelper)

        bookId = intent.getIntExtra("BOOK_ID", -1)
        userId = intent.getIntExtra("USER_ID", -1)

        val book = bookRepository.getBookById(bookId)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val publisherTextView = findViewById<TextView>(R.id.publisherTextView)
        val genreTextView = findViewById<TextView>(R.id.genreTextView)
        val editButton = findViewById<Button>(R.id.editButton)

        book?.let {
            titleTextView.text = it.title
            publisherTextView.text = it.publisher
            genreTextView.text = it.genre
        }

        editButton.setOnClickListener {
            val intent = Intent(this, BookEditActivity::class.java)
            intent.putExtra("BOOK_ID", bookId)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }
    }
}
