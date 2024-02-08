package com.example.l3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.l3.ui.theme.L3Theme
import com.example.l3.MainActivity
class DataActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DataActivity", "Dataaa")
        setContent {
            L3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemWindow(this, viewModel = appViewModel)
                }
            }
        }
    }
}

@Composable
fun ItemWindow(activity: ComponentActivity, viewModel: AppViewModel,  modifier: Modifier = Modifier) {
    Column (Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = viewModel.nuevaTarea,
            onValueChange = {viewModel.nuevaTarea = it},
            label = { Text(text = "Nuevo TODO")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(onClick = {
            viewModel.todoList.add(viewModel.nuevaTarea)
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }) {
            Text(text = stringResource(id = R.string.done))
        }

    }
}

