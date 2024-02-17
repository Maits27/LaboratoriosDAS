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

    var perdedor by mutableStateOf(false)
        private set

    var ultimoIntento: MutableList<Int> = mutableListOf()
    private set

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
            for (i in 0 until (4+nivelSeleccionado)){
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

    private fun intentoConsumido(){
        intentos--
    }

    private fun sumarIntento(numero: Int, muertos: Int, heridos: Int){
        listaIntentos.add(listOf(numero, muertos, heridos))
        ultimoIntento = listOf(numero, muertos, heridos).toMutableList()
    }

    fun comprobarNumero(numeroInsertado: Int): Boolean{
        var heridos = 0
        var muertos = 0

        var numerosQueHay = mutableListOf<String>()
        for (n in numero.toString()){
            numerosQueHay.add(n.toString())
        }

        val numeroS = numeroInsertado.toString()

        if (numeroS.length!=4+nivel){
            return true
        }else{
            for (i in 0..3+nivel){
                if (numeroS[i].toString() == numerosQueHay[i]){
                    muertos++
                }else{
                    if (numeroS[i].toString() in numerosQueHay){
                        heridos++
                    }
                }
            }
            sumarIntento(numeroInsertado, muertos, heridos)
            intentoConsumido()
            if (intentos==0){
                perdedor = true
            }
        }
        return false
    }

    fun reiniciar(){
        for (i in 0 until listaIntentos.size){
            listaIntentos.removeFirst()
        }
        nivel = 0
        numero = 0
        ejNum = ' '.toString()
        perdedor=false
    }
}


