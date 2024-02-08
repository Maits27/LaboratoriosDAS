package com.example.l3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.l3.ui.theme.L3Theme
import java.util.Locale

class AppViewModel : ViewModel() {
    var nuevaTarea by mutableStateOf("")
    val todoList = mutableStateListOf<String>()
}
class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "Me creo")
        setContent {
            L3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoList(
                        this,
                        viewModel = appViewModel
                    )
                }
            }
        }
    }
    private fun cambiarIdioma(codigo: String){
        resources.configuration.setLocale(Locale(codigo))
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)

        resources.configuration.locale = Locale(codigo)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }
}

@Composable
fun TodoList(activity: ComponentActivity, viewModel: AppViewModel, modifier: Modifier = Modifier) {
    Column (Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            Log.d("Button", "Dataaa")
            val intent = Intent(activity, DataActivity::class.java)
            activity.startActivity(intent)
            Log.d("MainActivity", "Dataaa")
        }) {
            Text(text = stringResource(id = R.string.add_task))
        }
    }
    LazyColumn (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Log.d("MainActivity", "Lazy")
        items(viewModel.todoList.size) { index ->
            val todo = viewModel.todoList[index]
            Button(onClick = { /* Handle button click if needed */ }) {
                Text(text = todo)
            }
        }
        Log.d("MainActivity", "TodoOk")
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview2() {
    L3Theme {
        TodoList(activity = ComponentActivity(), viewModel = AppViewModel())
    }
}