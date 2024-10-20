package com.example.notes.domain.usecase

import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NotesRepository

class AddNoteUseCase(
    private val repository: NotesRepository
) {
    fun execute(): Note{

        val note = Note()
        repository.addNote(note)

        return note
    }
}