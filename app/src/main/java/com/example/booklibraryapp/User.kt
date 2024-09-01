package com.example.booklibraryapp

import android.content.ContentValues

data class User(val id: Int, val name: String, val email: String)

class UserRepository(private val dbHelper: DatabaseHelper) {

    // Adicionar um novo usuário
    fun addUser(user: User): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("email", user.email)
        }
        return db.insert("user", null, values)
    }

    // Obter um usuário pelo ID
    fun getUserById(id: Int): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.query("user", null, "id=?", arrayOf(id.toString()), null, null, null)
        return if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            User(id, name, email)
        } else {
            null
        }.also {
            cursor.close()
        }
    }

    // Obter o último usuário registrado
    fun getLastUserId(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "user",
            arrayOf("id"),
            null,
            null,
            null,
            null,
            "id DESC",
            "1"
        )
        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            cursor.close()
            id
        } else {
            cursor.close()
            -1 // Indica que nenhum usuário foi encontrado
        }
    }

    // Atualizar um usuário existente
    fun updateUser(user: User): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("email", user.email)
        }
        return db.update("user", values, "id=?", arrayOf(user.id.toString()))
    }
}

