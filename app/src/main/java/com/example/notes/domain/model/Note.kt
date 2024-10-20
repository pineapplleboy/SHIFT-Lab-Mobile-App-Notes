package com.example.notes.domain.model

import java.util.UUID

class Note (
    var name: String = "New note",
    var content: String = "",
    val id: UUID = UUID.randomUUID()
)