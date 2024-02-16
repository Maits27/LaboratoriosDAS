package com.example.l4_nav

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class AppViewModel : ViewModel() {

    var intentos by mutableStateOf(0)
        private set
    var nivel by mutableStateOf(0)
        private set

    var numero by mutableStateOf(0)
        private set

    var listaIntentos: MutableList<List<Int>> = mutableListOf()
        private set
    var ejNum by mutableStateOf(' '.toString())

    fun setNivelSeleccionado(nivelSeleccionado: Int){
        if (nivelSeleccionado > 2) {
            nivel = 0
            intentos = 0
            numero = 0
        }
        else{
            nivel = nivelSeleccionado
            intentos = (nivelSeleccionado + 1) * 10
            numero = generarNumeroAleatorio(4+nivelSeleccionado)
            ejNum = ' '.toString()
            for (i in 1 until (4+nivelSeleccionado)){
                ejNum = ejNum + i.toString()
            }

        }
    }
    private fun generarNumeroAleatorio(x: Int): Int {
        require(x > 0) { "La cantidad de dígitos debe ser mayor que cero." }

        val min = 10.0.pow(x - 1).toInt()
        val max = 10.0.pow(x).toInt() - 1

        val random = java.util.Random()
        return random.nextInt(max - min + 1) + min
    }

    fun intentoConsumido(){
        intentos--
    }

    fun sumarIntento(numero: Int, muertos: Int, heridos: Int){
        listaIntentos.add(listOf(numero, muertos, heridos))
    }

    fun comprobarNumero(numero: Int){
        //TODO
    }
}