package com.enigmacamp.newsCompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.enigmacamp.newsCompose.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph(finishActivity = { finish() })
        }
    }
}

