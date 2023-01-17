package com.enigmacamp.newsCompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enigmacamp.newsCompose.repository.Category

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(14.dp)
            .clickable { onClick() }

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = category.name,
                modifier = Modifier.padding(14.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CategoryCard(category = Category.General, onClick = { TODO() })
}