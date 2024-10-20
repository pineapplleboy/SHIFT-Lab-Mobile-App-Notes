package com.example.notes.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.notes.data.model.NoteData
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NotesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotesRepositoryImpl(context: Context) : NotesRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("notes", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun getNotes(): List<Note> {
        val json = sharedPreferences.getString("note", null)
        return if (json != null) {
            val type = object : TypeToken<List<NoteData>>() {}.type
            val notesData: List<NoteData> = gson.fromJson(json, type)
            notesData.map { it.toDomain() }
        } else {
            emptyList()
        }
    }

    override fun addNote(note: Note) {
        val currentNotes = getNotes().map { it.toData() }.toMutableList()
        currentNotes.add(note.toData())

        val json = gson.toJson(currentNotes)
        sharedPreferences.edit().putString("note", json).apply()
    }

    override fun editNote(note: Note) {
        val currentNotes = getNotes().map { it.toData() }.toMutableList()

        val index = currentNotes.indexOfFirst { it.id == note.id }

        if (index != -1) {
            currentNotes[index] = note.toData()

            val json = gson.toJson(currentNotes)
            sharedPreferences.edit().putString("note", json).apply()
        }
    }

    override fun deleteNote(note: Note) {
        val currentNotes = getNotes().map { it.toData() }.toMutableList()

        val index = currentNotes.indexOfFirst { it.id == note.id }

        if (index != -1) {
            currentNotes.removeAt(index)

            val json = gson.toJson(currentNotes)
            sharedPreferences.edit().putString("note", json).apply()
        }
    }
}

fun Note.toData(): NoteData {
    return NoteData(
        name = this.name,
        content = this.content,
        id = this.id
    )
}

fun NoteData.toDomain(): Note {
    return Note(
        name = this.name,
        content = this.content,
        id = this.id
    )
}