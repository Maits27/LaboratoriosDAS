package com.example.l4_nav.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.l4_nav.AppViewModel
import com.example.l4_nav.R
import com.example.l4_nav.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaJuego(navController: NavController, appViewModel: AppViewModel){
    Scaffold {
        GameBodyContent(navController, appViewModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameBodyContent(navController: NavController, appViewModel: AppViewModel){

    var comprobacionIncorrecta by remember { mutableStateOf(false) }
    var cambio by remember { mutableStateOf(false) }
    var textoIngresado by remember { mutableStateOf("") }

    Scaffold (
        topBar = { ParteArriba(appViewModel) },
        bottomBar = { ParteAbajo(appViewModel, cambio) }
    ) {
        Column (
            Modifier
                .padding(16.dp)
                .fillMaxSize() ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = stringResource(id = R.string.introduceNum, (appViewModel.nivel+4)))
            OutlinedTextField(
                value = textoIngresado,
                onValueChange = {
                    textoIngresado = it
                },
                label = { Text(stringResource(id = R.string.ejNum, appViewModel.ejNum)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )


            Button(onClick = {
                if (!textoIngresado.isBlank()){
                    comprobacionIncorrecta = enviarIntento(
                        textoIngresado.toInt(),
                        appViewModel
                    )
                    if (!comprobacionIncorrecta){
                        cambio = !cambio
                    }
                }else{
                    comprobacionIncorrecta = true
                }
            }) {
                Text(stringResource(id = R.string.adivinar))
            }
            if(appViewModel.perdedor){
                var ultimo = appViewModel.ultimoIntento
                ToastMessage(message = stringResource(id = R.string.perdedor, ultimo[1], ultimo[2]))
                navController.navigateUp()
            }

            MensajeIncorrecto(
                show = comprobacionIncorrecta,
                onConfirm = { comprobacionIncorrecta = false },
                appViewModel.nivel+4
            )

        }

    }
}


@Composable
fun ParteArriba(appViewModel: AppViewModel){
    Column (
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        Row {
            Text(text = stringResource(id = R.string.nivel))
            if (appViewModel.nivel == 2){
                Text(text = stringResource(id = R.string.dificil))
            }else if (appViewModel.nivel == 1){
                Text(text = stringResource(id = R.string.medio))
            }else{
                Text(text = stringResource(id = R.string.facil))
            }
        }
        Text(text = stringResource(id = R.string.intentos, appViewModel.intentos))
    }
}


@Composable
fun ParteAbajo(appViewModel: AppViewModel, cambio: Boolean) {
    Column(
        Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.intentosAnteriores))
        Divider()
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.wrapContentSize()
        ) {
            items(appViewModel.listaIntentos) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.wrapContentSize()
                ){
                    Text(
                        text = stringResource(id = R.string.intentoX, it[0], it[1], it[2])
                    )
                }
            }
        }
    }
}


@Composable
fun MensajeIncorrecto(show: Boolean, onConfirm: () ->Unit, cantidad: Int){
    if(show){
        AlertDialog(
            onDismissRequest = {},
            confirmButton = { TextButton(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.done))
            }
            },
            title = {Text(text = stringResource(id = R.string.numIncorrecto))},
            text = { Text(text = stringResource(id = R.string.introduceNum, cantidad)) },
        )
    }
}


@Composable
fun ToastMessage(message: String) {
    var toast = Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
    toast.setMargin(160f, 160f)
    toast.setGravity(160, 100, 100)
    toast.show()
}


fun enviarIntento(numero: Int, appViewModel: AppViewModel): Boolean {
    return appViewModel.comprobarNumero(numero)
}
