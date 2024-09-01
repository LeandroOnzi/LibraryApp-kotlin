package com.example.booklibraryapp
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.booklibraryapp.DatabaseHelper
import com.example.booklibraryapp.R
import com.example.booklibraryapp.User
import com.example.booklibraryapp.UserRepository

class UserRegistrationActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        dbHelper = DatabaseHelper(this)
        userRepository = UserRepository(dbHelper)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()

            if (name.isNotBlank() && email.isNotBlank()) {
                val user = User(id = 0, name = name, email = email) // ID será gerado automaticamente
                userRepository.addUser(user)
                finish() // Voltar à tela anterior
            }
        }
    }
}

