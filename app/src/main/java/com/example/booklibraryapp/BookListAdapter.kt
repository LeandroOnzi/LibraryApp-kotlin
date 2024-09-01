package com.example.booklibraryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(
    private val books: List<Book>,
    private val userId: Int,
    private val onBookClick: (Int) -> Unit
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
        holder.itemView.setOnClickListener {
            onBookClick(book.id)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val publisherTextView: TextView = itemView.findViewById(R.id.publisherTextView)
        private val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)

        fun bind(book: Book) {
            titleTextView.text = book.title
            publisherTextView.text = book.publisher
            genreTextView.text = book.genre
        }
    }
}
