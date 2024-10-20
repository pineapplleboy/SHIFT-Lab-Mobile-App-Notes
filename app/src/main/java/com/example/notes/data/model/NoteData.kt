package com.example.notes.data.model

import java.util.UUID

class NoteData (
    val name: String = "New note",
    val content: String = "",
    val id: UUID = UUID.randomUUID()
)