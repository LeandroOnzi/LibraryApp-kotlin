package com.example.booklibraryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var userRepository: UserRepository
    private var userId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        dbHelper = DatabaseHelper(this)
        userRepository = UserRepository(dbHelper)

        // Receber userId da Intent
        userId = intent.getIntExtra("USER_ID", 1)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val user = userRepository.getUserById(userId)
        user?.let {
            nameEditText.setText(it.name)
            emailEditText.setText(it.email)
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()

            if (name.isNotBlank() && email.isNotBlank()) {
                val updatedUser = User(userId, name, email)
                userRepository.updateUser(updatedUser)
                finish() // Voltar à tela anterior
            }
        }
    }
}
