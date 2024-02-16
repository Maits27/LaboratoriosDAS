package com.example.l4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.l4.ui.theme.L4Theme

class MainActivity2 : ComponentActivity() {
    val appViewModel by viewModels<AppViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appViewModel.setNivelSeleccionado(intent?.extras?.getInt("nivel")?:0)
        setContent {
            L4Theme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Juego()
                }
            }
        }
    }
}

@Composable
fun Juego( modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun JuegoPreview() {
    L4Theme {
        Juego()
    }
}