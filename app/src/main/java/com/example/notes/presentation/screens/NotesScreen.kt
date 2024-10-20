package com.example.notes.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.R
import com.example.notes.domain.model.Note
import com.example.notes.presentation.components.NoteButton
import com.example.notes.presentation.components.NoteElement

@Composable
fun NotesScreen(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    addNote: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(37.dp),
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.background_dark)
            )
            .padding(
                top = 45.dp,
                end = 24.dp,
                start = 24.dp,
                bottom = 24.dp
            )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.notes),
                color = colorResource(id = R.color.white),
                fontFamily = FontFamily(Font(R.font.nunito_variablefont_wght, FontWeight.SemiBold)),
                fontSize = 43.sp
            )
            NoteButton(
                image = R.drawable.ic_add,
                contentDescription = "Add button"
            ) {
                addNote()
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier
        ) {
            items(notes) { note ->
                NoteElement(note.name) {
                    onNoteClick(note)
                }
            }
        }
    }
}