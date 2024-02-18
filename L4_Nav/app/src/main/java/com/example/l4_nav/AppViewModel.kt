package com.example.l4_nav

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class AppViewModel : ViewModel() {

    var state by mutableStateOf(AppState())
    private set
    var intentos by mutableStateOf(0)
        private set
    var nivel by mutableStateOf(0)
        private set

    var numero by mutableStateOf(0)
        private set

    var listaIntentos: MutableList<List<Int>> = mutableListOf()
        private set

    var ejNum by mutableStateOf("")

    var fallo by mutableStateOf(false)
        private set
    var ganador by mutableStateOf(false)
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
            generarNumeroAleatorio(4+nivelSeleccionado)
            ejNum = ""
            for (i in 1 .. (4+nivelSeleccionado)){
                ejNum = ejNum + i.toString()
            }
            changeName(numero.toString())
        }
    }
    private fun generarNumeroAleatorio(x: Int) {
        require(x > 0) { "La cantidad de dígitos debe ser mayor que cero." }

        val min = 10.0.pow(x - 1).toInt()
        val max = 10.0.pow(x).toInt() - 1

        val random = java.util.Random()
        val n = random.nextInt(max - min + 1) + min
        numero = n
    }

    private fun intentoConsumido(){
        intentos--
    }

    private fun sumarIntento(numero: Int, muertos: Int, heridos: Int){
        listaIntentos.add(listOf(numero, muertos, heridos))
        ultimoIntento = listOf(numero, muertos, heridos).toMutableList()
    }

    fun enviarNotificacionGanador(
        context: Context,
        titulo: String,
        description: String
    ){
        val notificationManager = context.getSystemService(NotificationManager::class.java)
//        val abrirOtraParteIntent = Intent(context, MainActivity::class.java)
//        val abrirOtraPartePendingIntent = PendingIntent.getActivity(
//            context,
//            0,
//            abrirOtraParteIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        // Intent para cerrar la app
//        val cerrarAppIntent = Intent(context, CerrarAppReceiver::class.java)
//        val cerrarAppPendingIntent = PendingIntent.getBroadcast(
//            context,
//            0,
//            cerrarAppIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )

        var notification = NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
            .setContentTitle(titulo)
            .setContentText(description)
            .setSmallIcon(R.drawable.baseline_auto_awesome_24)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(description))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            // Agregar acciones (botones)
//            .addAction(R.drawable.ic_abrir_otra_parte, "Abrir Otra Parte", abrirOtraPartePendingIntent)
//            .addAction(R.drawable.ic_cerrar_app, "Cerrar App", cerrarAppPendingIntent)
            .build()

        notificationManager.notify(state.name.hashCode(), notification)
    }
    fun comprobarNumero(numeroInsertado: Int): Boolean{
        fallo = false
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
            if(numero.toString() == numeroInsertado.toString()){
                ganador = true
            }else{
                fallo = true
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
        ejNum = ""
        fallo = false
        ganador = false
    }
    fun changeName(text: String) {
        state = state.copy(
            name = text
        )
    }
}


