package com.example.notes.domain.usecase

import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NotesRepository

class EditNoteUseCase(
    private val repository: NotesRepository
) {
    fun execute(note: Note){
        repository.editNote(note)
    }
}