package com.enigmacamp.newsCompose.common

//sealed class UiState<out T>(
//    val data: T? = null,
//    val errorMessage: String? = null,
//    val isInit: Boolean? = false
//) {
//    class Init(isInit: Boolean = true) : UiState<Nothing>(null, null, isInit)
//    class Success<T>(data: T?) : UiState<T>(data)
//    object Loading : UiState<Nothing>()
//    class Error(errorMessage: String?) : UiState<Nothing>(errorMessage = errorMessage)
//}

sealed class UiState<out T : Any>(val isInit: Boolean? = false) {
    class Init(init: Boolean = true) : UiState<Nothing>(init)
    class Success<out T : Any>(val data: T?) : UiState<T>()
    object Loading : UiState<Nothing>()
    class Error(val errorMessage: String?) : UiState<Nothing>()
}