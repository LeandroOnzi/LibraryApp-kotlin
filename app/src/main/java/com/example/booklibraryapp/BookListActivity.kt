package com.example.booklibraryapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookListActivity : AppCompatActivity() {

    private lateinit var bookRepository: BookRepository
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // Inicialize o repositório
        val dbHelper = DatabaseHelper(this)
        bookRepository = BookRepository(dbHelper)

        // Receba o ID do usuário da Intent
        userId = intent.getIntExtra("USER_ID", -1)

        // Verifique se o ID do usuário é válido
        if (userId == -1) {
            // Trate o caso em que o ID do usuário não é válido
            showError("User ID not found.")
            return
        }

        // Obtenha a lista de livros do usuário
        val books = bookRepository.getBooksByUserId(userId)

        // Exiba a lista de livros
        val bookListTextView = findViewById<TextView>(R.id.bookListTextView)
        bookListTextView.text = books.joinToString("\n") { "${it.title} - ${it.publisher} - ${it.genre}" }
    }

    private fun showError(message: String) {
        // Exiba uma mensagem de erro ou trate o erro de outra forma
        val errorTextView = findViewById<TextView>(R.id.bookListTextView)
        errorTextView.text = message
    }
}
