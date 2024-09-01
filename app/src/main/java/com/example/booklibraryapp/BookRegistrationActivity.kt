package com.example.booklibraryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

// BookRegistrationActivity.kt
class BookRegistrationActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var bookRepository: BookRepository
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_registration)

        dbHelper = DatabaseHelper(this)
        bookRepository = BookRepository(dbHelper)

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val publisherEditText = findViewById<EditText>(R.id.publisherEditText)
        val genreEditText = findViewById<EditText>(R.id.genreEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val publisher = publisherEditText.text.toString()
            val genre = genreEditText.text.toString()

            if (title.isNotBlank() && publisher.isNotBlank() && genre.isNotBlank()) {
                val book = Book(id = 0, title = title, publisher = publisher, genre = genre, userId = userId)
                bookRepository.addBook(book)
                finish() // Voltar à tela anterior
            }
        }
    }
}

