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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.R
import com.example.notes.domain.model.Note
import com.example.notes.presentation.components.NoteButton

@Composable
fun FullNote(
    note: Note,
    modifier: Modifier = Modifier,
    onNoteSave: (Note) -> Unit,
    onReturn: () -> Unit,
    onNoteDelete: (Note) -> Unit
) {
    var name by remember { mutableStateOf(note.name) }
    var content by remember { mutableStateOf(note.content) }
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(37.dp),
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.background_dark)
            )
            .padding(
                top = 75.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NoteButton(
                image = R.drawable.ic_return,
                contentDescription = "Return button"
            ) {
                onReturn()
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NoteButton(
                    image = R.drawable.ic_save,
                    contentDescription = "Save button"
                ) {
                    onNoteSave(note)
                }

                NoteButton(
                    image = R.drawable.ic_delete,
                    contentDescription = "Delete button"
                ) {
                    onNoteDelete(note)
                }
            }
        }

        LazyColumn {
            item {
                TextField(
                    value = name,
                    onValueChange = { newValue ->
                        name = newValue
                        note.name = name
                    },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.white),
                        fontFamily = FontFamily(Font(R.font.nunito_variablefont_wght)),
                        fontSize = 35.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorResource(id = android.R.color.transparent),
                        focusedContainerColor = colorResource(id = android.R.color.transparent)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }

            item {
                TextField(
                    value = content,
                    onValueChange = { newValue ->
                        content = newValue
                        note.content = content
                    },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.white),
                        fontFamily = FontFamily(Font(R.font.nunito_variablefont_wght)),
                        fontSize = 23.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorResource(id = android.R.color.transparent),
                        focusedContainerColor = colorResource(id = android.R.color.transparent)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
        }
    }
}