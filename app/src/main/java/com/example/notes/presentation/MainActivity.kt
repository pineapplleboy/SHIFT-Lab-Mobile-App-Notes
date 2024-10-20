package com.example.notes.presentation

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.notes.data.repository.NotesRepositoryImpl
import com.example.notes.domain.model.Note
import com.example.notes.domain.usecase.AddNoteUseCase
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.EditNoteUseCase
import com.example.notes.domain.usecase.GetNotesUseCase
import com.example.notes.presentation.screens.FullNote
import com.example.notes.presentation.screens.NotesScreen
import com.example.notes.ui.theme.NotesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            NotesTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val repository = NotesRepositoryImpl(this)

                    val getNotesUseCase = GetNotesUseCase(repository)
                    val addNoteUseCase = AddNoteUseCase(repository)
                    val editNoteUseCase = EditNoteUseCase(repository)
                    val deleteNoteUseCase = DeleteNoteUseCase(repository)

                    val notes = getNotesUseCase.execute()

                    var noteOnScreen by remember {
                        mutableStateOf(false)
                    }

                    var currentNote by remember {
                        mutableStateOf(Note())
                    }

                    if (!noteOnScreen) {
                        NotesScreen(
                            notes,
                            addNote = {
                                currentNote = addNoteUseCase.execute()
                                noteOnScreen = true
                            },
                            onNoteClick = {
                                currentNote = it
                                noteOnScreen = true
                            })
                    } else {
                        FullNote(
                            note = currentNote,
                            onNoteSave = {
                                editNoteUseCase.execute(it)
                            },
                            onReturn = {
                                noteOnScreen = false
                            },
                            onNoteDelete = {
                                deleteNoteUseCase.execute(it)
                                noteOnScreen = false
                            })
                    }
                }
            }
        }
    }
}