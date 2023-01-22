package com.enigmacamp.newsCompose.ui.screens.article

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.enigmacamp.newsCompose.ui.components.Loading
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleWebView(url: String) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    val webViewState = rememberWebViewState(url)
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = url) })
    }) {
        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }


                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    isLoading = false
                }
            }
        }
        WebView(
            state = webViewState,
            onCreated = { it.settings.javaScriptEnabled = true },
            client = webClient

        )
        if (isLoading) {
            Log.d("Page", "Loadiiiing")
            Loading()
        }
    }
}