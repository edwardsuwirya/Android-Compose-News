package com.enigmacamp.newsCompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enigmacamp.newsCompose.model.Source

@Composable
fun SourceCard(source: Source, onClick: (Source) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(source) }, elevation = 5.dp, backgroundColor = Color.White
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = source.title,
                style = TextStyle(
                    fontSize = 14.sp
                ),
            )
            Text(
                text = source.description,
                style = TextStyle(
                    fontSize = 12.sp
                ),
            )
        }
    }
}