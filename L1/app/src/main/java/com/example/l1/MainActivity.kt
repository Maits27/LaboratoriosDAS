 package com.example.l1

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.l1.ui.theme.L1Theme
import kotlin.random.Random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory

 class Labo1ViewModel : ViewModel() {
     var posiciones by mutableStateOf(true)
     var pulsacionesLayout1 by mutableStateOf(0)
     var colorFondoLayout1 by mutableStateOf(Color.White)
     var textosVisiblesLayout2 by mutableStateOf(List(3) { true })
     var textoEnviado by mutableStateOf("")
 }


 class MainActivity : ComponentActivity() {
     private val laboViewModel: Labo1ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            L1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Labo1(
                        modifier = Modifier.fillMaxSize(), viewModel = laboViewModel
                    )
                }
            }
        }
    }
}

 @Composable
 fun Labo1(modifier: Modifier = Modifier, viewModel: Labo1ViewModel) {
     val laboViewModel: Labo1ViewModel = viewModel

     Column(
         modifier = modifier.background(Color.White),
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         if (laboViewModel.posiciones) {
             Layout1(
                 modifier = Modifier.wrapContentSize(),
                 pulsaciones = laboViewModel.pulsacionesLayout1,
                 colorFondo = laboViewModel.colorFondoLayout1,
                 onPulsacionChange = { laboViewModel.pulsacionesLayout1 = it },
                 onColorChange = { laboViewModel.colorFondoLayout1 = it },
                 onEnviarClick = { laboViewModel.textoEnviado = it } // Implementación de la función onEnviarClick
             )
             Layout2(
                 modifier = Modifier.wrapContentSize(),
                 textosVisibles = laboViewModel.textosVisiblesLayout2,
                 onVisibilidadChange = { laboViewModel.textosVisiblesLayout2 = it },
                 textoEnviado = laboViewModel.textoEnviado // Pasar el texto enviado a Layout2
             )
         } else {
             Layout2(
                 modifier = Modifier.wrapContentSize(),
                 textosVisibles = laboViewModel.textosVisiblesLayout2,
                 onVisibilidadChange = { laboViewModel.textosVisiblesLayout2 = it },
                 textoEnviado = laboViewModel.textoEnviado // Pasar el texto enviado a Layout2
             )
             Layout1(
                 modifier = Modifier.wrapContentSize(),
                 pulsaciones = laboViewModel.pulsacionesLayout1,
                 colorFondo = laboViewModel.colorFondoLayout1,
                 onPulsacionChange = { laboViewModel.pulsacionesLayout1 = it },
                 onColorChange = { laboViewModel.colorFondoLayout1 = it },
                 onEnviarClick = { laboViewModel.textoEnviado = it } // Implementación de la función onEnviarClick
             )
         }
         Button(
             onClick = {
                 laboViewModel.posiciones = !laboViewModel.posiciones
             },
             Modifier.padding(16.dp),
         ) {
             Text(text = "Swap")
         }
     }
 }

 @Composable
 fun Layout1(
     modifier: Modifier = Modifier,
     pulsaciones: Int,
     colorFondo: Color,
     onPulsacionChange: (Int) -> Unit,
     onColorChange: (Color) -> Unit,
     onEnviarClick: (String) -> Unit // Nueva función para manejar el clic en el botón "Enviar"
 ) {
     var textoEditText by remember { mutableStateOf(TextFieldValue()) }

     Column(
         Modifier
             .background(color = colorFondo)
             .fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Top
     ) {
         TextField(
             value = textoEditText,
             onValueChange = {
                 textoEditText = it
             },
             label = { Text("Ingrese texto") },
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(16.dp)
         )

         Button(
             onClick = {
                 onEnviarClick(textoEditText.text) // Llamada a la función cuando se hace clic en "Enviar"
             },
             Modifier
                 .padding(8.dp, 16.dp)
         ) {
             Text(text = "Enviar")
         }

         Row {
             Text(color = Color.Black, text = "Texto 1")
             Text(color = Color.Black, text = "Texto 2")
             Text(color = Color.Black, text = "Texto 3")
         }

         Button(
             onClick = {
                 onPulsacionChange(pulsaciones + 1)
                 onColorChange(generateRandomColor())
             },
             Modifier
                 .wrapContentSize()
                 .padding(16.dp)
         ) {
             Text(text = "Cambio de color número: $pulsaciones")
         }
     }
 }

 @Composable
 fun Layout2(
     modifier: Modifier = Modifier,
     textosVisibles: List<Boolean>,
     onVisibilidadChange: (List<Boolean>) -> Unit,
     textoEnviado: String // Nuevo parámetro para recibir el texto enviado desde Layout1
 ) {
     Column(
         Modifier
             .background(color = Color.White)
             .fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Top
     ) {
         Column(horizontalAlignment = Alignment.CenterHorizontally) {
             for (i in 0 until 3) {
                 if (textosVisibles[i]) {
                     when (i) {
                         0 -> Text(color = Color.Black, text = "Texto 1")
                         1 -> Text(color = Color.Black, text = "Texto 2")
                         2 -> Text(color = Color.Black, text = "Texto 3")
                     }
                 }
             }

             // Mostrar el texto enviado en un TextView o cualquier otro componente según sea necesario
             Text(text = "Texto enviado: $textoEnviado", color = Color.Black)

             Button(
                 onClick = {
                     val indiceRandom = (0 until 3).random()
                     onVisibilidadChange(textosVisibles.toMutableList().apply {
                         set(indiceRandom, !get(indiceRandom))
                     })
                 },
                 Modifier
                     .wrapContentSize()
                     .padding(16.dp)
             ) {
                 Text(text = "Cambiar Visibilidad")
             }
         }
     }
 }



 fun generateRandomColor(): Color {
     return Color(
         red = Random.nextFloat(),
         green = Random.nextFloat(),
         blue = Random.nextFloat()
     )
 }


@Preview(showBackground = true)
@Composable
fun FirstApp() {
    Labo1(
        modifier = Modifier.fillMaxSize(), viewModel = Labo1ViewModel()
    )
}