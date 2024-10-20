package com.example.notes.domain.usecase

import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NotesRepository

class GetNotesUseCase(
    private val repository: NotesRepository
) {
    fun execute(): List<Note> {
        return repository.getNotes()
    }
}