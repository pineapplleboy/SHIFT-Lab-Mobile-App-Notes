package com.example.notes.domain.repository

import com.example.notes.domain.model.Note

interface NotesRepository {
    fun getNotes(): List<Note>

    fun addNote(note: Note)

    fun editNote(note: Note)

    fun deleteNote(note: Note)
}