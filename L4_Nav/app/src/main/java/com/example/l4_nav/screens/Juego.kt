package com.example.l4_nav.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var textoIngresado by remember { mutableStateOf("") }
    Scaffold (
        topBar = { ParteArriba(appViewModel) },
        bottomBar = { ParteAbajo(appViewModel) }
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

            // Botón para guardar
            Button(onClick = {
                enviarIntento(textoIngresado)
            }) {
                Text(stringResource(id = R.string.adivinar))
            }
        }
    }
}

fun enviarIntento(numero: String){
    //TODO
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
fun ParteAbajo(appViewModel: AppViewModel){
    Column (
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stringResource(id = R.string.intentosAnteriores))
        Divider()
        val intentos = appViewModel.listaIntentos
        for (intento in intentos){
            Text(text = stringResource(id = R.string.intentoX, intento[0], intento[1], intento[2]))
        }
    }
}


