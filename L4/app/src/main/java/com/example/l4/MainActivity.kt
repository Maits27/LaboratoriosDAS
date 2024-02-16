package com.example.l4

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l4.ui.theme.L4Theme


class MainActivity : ComponentActivity() {

    private var nivel: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            L4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InicioJuego(
                        ::instrucciones,
                        ::iniciarJuego,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(R.color.lightBlue))
                    )
                }
            }
        }
    }

    private fun instrucciones() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.instrucciones)
        builder.setMessage(R.string.instruccionesCompletas)
        builder.setPositiveButton(R.string.done, null) // Puedes agregar un botón de OK o cualquier otro botón que desees
        builder.show() // Importante: muestra el diálogo después de configurarlo

    }
    private fun iniciarJuego() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.seleccionNivel)

        // Añadir botones al diálogo
        builder.setNeutralButton(R.string.facil) { _, _ ->
            // Código a ejecutar cuando se hace clic en el botón Fácil
            // Puedes implementar lógica relacionada con el nivel Fácil aquí
            nivel = 0
            abrirMainActivity2()
        }

        builder.setNegativeButton(R.string.medio) { _, _ ->
            // Código a ejecutar cuando se hace clic en el botón Intermedio
            // Puedes implementar lógica relacionada con el nivel Intermedio aquí
            nivel = 1
            abrirMainActivity2()
        }

        builder.setPositiveButton(R.string.dificil) { _, _ ->
            // Código a ejecutar cuando se hace clic en el botón Difícil
            // Puedes implementar lógica relacionada con el nivel Difícil aquí
            nivel = 2
            abrirMainActivity2()
        }

        builder.show() // Importante: muestra el diálogo después de configurarlo
    }

    private fun abrirMainActivity2() {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("nivel", nivel)
        startActivity(intent)
        finish()
    }


}

@Composable
fun InicioJuego(instrucciones: ()-> Unit, iniciarJuego: ()-> Unit, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.title),
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 50.dp)
                .wrapContentSize(),
            fontSize = 25.sp
        )
        Button(
            onClick = {
                      iniciarJuego()
//                val builder = AlertDialog.Builder(activity)
//                builder.setTitle(R.string.seleccionNivel)
//
//                // Crear un LinearLayout vertical
//                val linearLayout = LinearLayout(activity)
//                linearLayout.orientation = LinearLayout.VERTICAL
//                linearLayout.layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//                )
//
//                // Añadir botones al LinearLayout
//                val botonFacil = android.widget.Button(activity)
//                botonFacil.text = "Facil"
//                botonFacil.setOnClickListener {
//                    // Código para el botón Fácil
//                    // Puedes implementar la lógica aquí
//                    appViewModel.setNivelSeleccionado(0)
//                }
//                linearLayout.addView(botonFacil)
//
//                val botonIntermedio = android.widget.Button(activity)
//                botonIntermedio.text = "Medio"
//                botonIntermedio.setOnClickListener {
//                    // Código para el botón Intermedio
//                    // Puedes implementar la lógica aquí
//                    appViewModel.setNivelSeleccionado(1)
//                }
//                linearLayout.addView(botonIntermedio)
//
//                val botonDificil = android.widget.Button(activity)
//                botonDificil.text = "Dificil"
//                botonDificil.setOnClickListener {
//                    // Código para el botón Difícil
//                    // Puedes implementar la lógica aquí
//                    appViewModel.setNivelSeleccionado(2)
//                }
//                linearLayout.addView(botonDificil)
//
//                // Añadir el LinearLayout al diálogo
//                builder.setView(linearLayout)
//                builder.show()
                },
            Modifier.padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.jugar))
        }
        Button(
            onClick = { instrucciones() },
            Modifier.padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.instrucciones))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AppPreview() {
//    L4Theme {
//        InicioJuego()
//    }
//}