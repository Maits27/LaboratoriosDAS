package com.example.l2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.l2.ui.theme.L2Theme
import androidx.compose.runtime.*
import java.util.*


enum class AppLanguage(val language: String, val code: String) {
    EN("English", "en"),
    ES("Español", "es"),
}
enum class AppLifecycleState {
    OnCreate,
    OnStart,
    OnResume,
    OnRestart,
    OnPause,
    OnStop,
    OnDestroy
}
class ViewModel2 : ViewModel() {
    var cambios by mutableStateOf(0) // Delega los setters y getters a mutable state of
    val transitionedStates = mutableStateListOf<AppLifecycleState>()
    val transitionedStatesCounter = mutableStateMapOf<AppLifecycleState, Int>()
}

class MainActivity : ComponentActivity() {
    private val viewModel2 by viewModels<ViewModel2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            L2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Labo2(cambios = viewModel2.cambios)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel2.cambios++
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnPause] = viewModel2.transitionedStatesCounter[AppLifecycleState.OnPause]?.plus(1) ?: 1
    }

    override fun onStart() {
        super.onStart()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnStart] = viewModel2.transitionedStatesCounter[AppLifecycleState.OnStart]?.plus(1) ?: 1
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnDestroy] = viewModel2.transitionedStatesCounter[AppLifecycleState.OnDestroy]?.plus(1) ?: 1
    }

    override fun onStop() {
        super.onStop()
        viewModel2.transitionedStatesCounter[AppLifecycleState.OnStop] = viewModel2.transitionedStatesCounter[AppLifecycleState.OnStop]?.plus(1) ?: 1
    }
    /*
    override fun onConfigurationChanged(newConfig: Configuration) { //Stop
        super.onConfigurationChanged(newConfig)
        viewModel2.cambios++
        Log.d("MainActivity", "onConfigurationChanged called!!!!!!!") // Agregar este log
    }
     */
}

@Composable
fun Labo2(cambios: Int, modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Log.d("MainActivity", " L2!!!!!!!!!!!!") // Agregar este log
        Text(text = "Ha habido $cambios cambios en la pantalla.")
    }
}
