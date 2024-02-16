package com.example.l4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    var nivel by mutableStateOf(0)
    private set

    fun setNivelSeleccionado(nivelSeleccionado: Int){
        if (nivelSeleccionado > 2) nivel = 0
        else  nivel = nivelSeleccionado
    }
}