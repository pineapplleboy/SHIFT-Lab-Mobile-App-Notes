package com.example.notes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.R

@Composable
fun NoteElement(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Text(
        text = name,
        color = colorResource(id = R.color.black),
        fontSize = 25.sp,
        fontFamily = FontFamily(Font(R.font.nunito_variablefont_wght)),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.yellow_note),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 40.dp,
                vertical = 20.dp
            )
            .clickable {
                onClick()
            }
    )
}