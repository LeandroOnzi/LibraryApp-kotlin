package com.example.booklibraryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UserEditActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit)

        dbHelper = DatabaseHelper(this)
        userRepository = UserRepository(dbHelper)

        val userId = getCurrentUserId()
        val user = userRepository.getUserById(userId)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)

        user?.let {
            nameEditText.setText(it.name)
            emailEditText.setText(it.email)
        }

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedEmail = emailEditText.text.toString()

            if (updatedName.isNotBlank() && updatedEmail.isNotBlank()) {
                val updatedUser = User(id = userId, name = updatedName, email = updatedEmail)
                userRepository.updateUser(updatedUser)
                finish()
            }
        }
    }

    private fun getCurrentUserId(): Int {
        return 1
    }
}
