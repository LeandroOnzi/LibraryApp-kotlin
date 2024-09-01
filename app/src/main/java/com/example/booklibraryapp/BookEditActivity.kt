package com.example.booklibraryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class BookEditActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var bookRepository: BookRepository
    private var bookId: Int = -1
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_edit)

        dbHelper = DatabaseHelper(this)
        bookRepository = BookRepository(dbHelper)

        bookId = intent.getIntExtra("BOOK_ID", -1)
        userId = intent.getIntExtra("USER_ID", -1)

        val book = bookRepository.getBookById(bookId)

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val publisherEditText = findViewById<EditText>(R.id.publisherEditText)
        val genreEditText = findViewById<EditText>(R.id.genreEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        book?.let {
            titleEditText.setText(it.title)
            publisherEditText.setText(it.publisher)
            genreEditText.setText(it.genre)
        }

        saveButton.setOnClickListener {
            val updatedTitle = titleEditText.text.toString()
            val updatedPublisher = publisherEditText.text.toString()
            val updatedGenre = genreEditText.text.toString()

            if (updatedTitle.isNotBlank() && updatedPublisher.isNotBlank() && updatedGenre.isNotBlank()) {
                val updatedBook = Book(id = bookId, title = updatedTitle, publisher = updatedPublisher, genre = updatedGenre, userId = userId)
                bookRepository.updateBook(updatedBook)
                finish()
            }
        }
    }
}
