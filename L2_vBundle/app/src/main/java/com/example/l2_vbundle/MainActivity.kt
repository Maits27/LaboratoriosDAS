package com.example.l2_vbundle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.l2_vbundle.ui.theme.L2_vBundleTheme
import java.util.Locale


enum class AppLanguage(val language: String, val code: String) {
    EN("English", "en"),
    ES("Español", "es"),
    EU("Euskera", "eu")
}
enum class AppLifecycleState {
    OnStart,
    OnSaveInstanceState,
    OnCreate,
    OnResume,
    OnPause,
    OnStop,
    OnDestroy,
    OnRestart
}
class ViewModel2 : ViewModel() {
    val transitionedStates = mutableStateListOf<AppLifecycleState>()
    val transitionedStatesCounter = mutableStateMapOf<AppLifecycleState, Int>()
    var idioma by mutableStateOf("es")
}

class MainActivity : ComponentActivity() {
    private val viewModel2 by viewModels<ViewModel2>()
    var numeroDeGiros: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {  // TODO: EN ESTE CASO SE USARÁ ESTE PARÁMETRO
        super.onCreate(savedInstanceState)

        numeroDeGiros = savedInstanceState?.getInt("numeroGiros") ?:0

        viewModel2.transitionedStatesCounter[AppLifecycleState.OnCreate] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnCreate]?.plus(1) ?: 1
        setContent {
            L2_vBundleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Labo2(
                        cambios = numeroDeGiros,
                        contador = viewModel2.transitionedStatesCounter,
                        idioma = viewModel2.idioma,
                        onLanguageChange = {
                            viewModel2.idioma = it
                            camcambiarIdioma(viewModel2.idioma)
                        }
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnPause] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnPause]?.plus(1) ?: 1
    }

    override fun onStart() {
        super.onStart()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnStart] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnStart]?.plus(1) ?: 1
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnDestroy] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnDestroy]?.plus(1) ?: 1
    }

    override fun onStop() {
        super.onStop()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnStop] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnStop]?.plus(1) ?: 1
    }

    override fun onRestart() {
        super.onRestart()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnRestart] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnRestart]?.plus(1) ?: 1
    }

    override fun onResume() {
        super.onResume()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnResume] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnResume]?.plus(1) ?: 1
    }

    //TODO DIFERENTE:
    override fun onSaveInstanceState (savedInstanceState: Bundle) {
        // Llamamos a la función que heredamos
        super.onSaveInstanceState(savedInstanceState)

        // Actualizamos el número de giros
        savedInstanceState.putInt("numeroGiros", this.numeroDeGiros + 1)
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnSaveInstanceState] =
            viewModel2.transitionedStatesCounter[AppLifecycleState.OnSaveInstanceState]?.plus(1) ?: 1
    }

    private fun camcambiarIdioma(codigo: String){
        resources.configuration.setLocale(Locale(codigo))
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)

        resources.configuration.locale = Locale(codigo)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }

}

@Composable
fun Labo2(cambios: Int, contador: Map<AppLifecycleState, Int>, idioma: String, onLanguageChange:(String)->Unit, modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.contador_mensaje, cambios))
        Column (
            Modifier
                .wrapContentSize()
                .background(color = Color.LightGray)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            for (state in AppLifecycleState.values()) {
                Text(text = stringResource(R.string.estado_mensaje, state, contador[state]?:0), Modifier.padding(top = 5.dp, bottom = 5.dp))
            }
        }
        Row {
            for (language in AppLanguage.values()) {
                if (language.code != idioma){
                    Button(onClick = {
                        onLanguageChange(language.code)
                        // Cambia el idioma al pulsar el botón
                    }) {
                        Text(text = "$language")
                    }
                }
            }
        }
        //TODO: FALTARIA LA SEGUNDA ACTIVIDAD Y EL BOTON DE CAMBIO PERO SE QUEDA ASI
    }
}

