package com.enigmacamp.newsCompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enigmacamp.newsCompose.model.Source

@Composable
fun SourceCard(source: Source, onClick: (Source) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(source) },
        elevation = 5.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(15.dp)
    ) {

        Surface(modifier = Modifier.padding(8.dp)) {
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = source.title,
                    style = TextStyle(
                        fontSize = 18.sp, fontWeight = FontWeight(20)
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
}