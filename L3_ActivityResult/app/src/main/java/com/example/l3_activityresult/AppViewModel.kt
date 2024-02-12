package com.example.l3_activityresult

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _forceRefresh = MutableStateFlow(false)
    val forceRefresh: StateFlow<Boolean>
        get() = _forceRefresh

    //    var nuevaTarea by mutableStateOf("")
    var todoList by mutableStateOf(arrayListOf<String>()) //String

    //    init {
//        viewModelScope.launch {
//            todoList.add("")
//            todoList.removeAll()
//        }
//    }
    fun triggerRefresh() {
        _forceRefresh.value = true
    }

    // Esta función se llama cuando se ha completado la actualización
    fun refreshComplete() {
        _forceRefresh.value = false
    }
}