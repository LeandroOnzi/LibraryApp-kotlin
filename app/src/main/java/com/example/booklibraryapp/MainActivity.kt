package com.example.booklibraryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        userRepository = UserRepository(dbHelper)

        val userRegistrationButton = findViewById<Button>(R.id.userRegistrationButton)
        val bookRegistrationButton = findViewById<Button>(R.id.bookRegistrationButton)
        val bookListButton = findViewById<Button>(R.id.bookListButton)
        val userProfileButton = findViewById<Button>(R.id.userProfileButton)

        userRegistrationButton.setOnClickListener {
            startActivity(Intent(this, UserRegistrationActivity::class.java))
        }

        bookRegistrationButton.setOnClickListener {
            startActivity(Intent(this, BookRegistrationActivity::class.java))
        }

        dbHelper.printDatabaseContent()

        bookListButton.setOnClickListener {
            val userId = getUserId()
            if (userId != -1) {
                val intent = Intent(this, BookListActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
            } else {
                // Tratamento de erro, se necessário
            }
        }

        userProfileButton.setOnClickListener {
            val userId = getUserId()
            if (userId != -1) {
                val intent = Intent(this, UserProfileActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
            } else {
                // Tratamento de erro, se necessário
            }
        }
    }

    private fun getUserId(): Int {
        return userRepository.getLastUserId()
    }
}

