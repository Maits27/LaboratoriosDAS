package com.example.l4_nav.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.l4_nav.AppViewModel
import com.example.l4_nav.MainActivity
import com.example.l4_nav.R
import com.example.l4_nav.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaPrincipal(navController: NavController, appViewModel: AppViewModel){
    Scaffold {
        BodyContent(navController, appViewModel)
    }
}

@Composable
fun BodyContent(navController: NavController, appViewModel: AppViewModel){
    var showInstructions by rememberSaveable { mutableStateOf(false) }
    var goToGame by rememberSaveable { mutableStateOf(false) }
    Column (
        modifier = Modifier.fillMaxSize(),
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
                goToGame = true
            },
            Modifier.padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.jugar))
        }
        Button(
            onClick = {
                showInstructions = true
            },
            Modifier.padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.instrucciones))
        }
        Instrucciones(
            show = showInstructions,
            onConfirm = {showInstructions = false}
        )
        EleccionJuego(show = goToGame,
            onDismiss = {goToGame = false},
            appViewModel,
            navController
        )
    }
}

@Composable
fun Instrucciones( show: Boolean, onConfirm: () -> Unit) {
    if(show){
        AlertDialog(
            onDismissRequest = {},
            confirmButton = { TextButton(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.done))
            }},
            title = { Text(text = stringResource(id = R.string.instrucciones)) },
            text = {
                Text(text = stringResource(id = R.string.instruccionesCompletas))
            }
        )
    }
}

@Composable
fun EleccionJuego(show: Boolean, onDismiss: ()-> Unit, appViewModel: AppViewModel, navController: NavController) {
    if(show){
        AlertDialog(
            onDismissRequest = {},
            confirmButton = { TextButton(onClick = { onDismiss() }){
                Text(text = stringResource(id = R.string.volver))
            }},
            title = { Text(text = stringResource(id = R.string.seleccionNivel)) },
            text = {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = {
                            onDismiss()
                            appViewModel.setNivelSeleccionado(0)
                            navController.navigate(AppScreens.PantallaJuego.route) },
                        Modifier.fillMaxWidth()
                        ) {
                        Text(text = stringResource(id = R.string.facil))
                    }
                    Button(
                        onClick = {
                            onDismiss()
                            appViewModel.setNivelSeleccionado(1)
                            navController.navigate(AppScreens.PantallaJuego.route)  },
                        Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.medio))
                    }
                    Button(
                        onClick = {
                            onDismiss()
                            appViewModel.setNivelSeleccionado(2)
                            navController.navigate(AppScreens.PantallaJuego.route)  },
                        Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.dificil))
                    }
                }
            }
        )
    }
}