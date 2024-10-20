package com.example.notes.domain.usecase

import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NotesRepository

class DeleteNoteUseCase (
    private val repository: NotesRepository
){
    fun execute(note: Note){
        repository.deleteNote(note)
    }
}