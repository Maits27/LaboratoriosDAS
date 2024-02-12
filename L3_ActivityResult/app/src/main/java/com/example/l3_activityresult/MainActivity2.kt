package com.example.l3_activityresult

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.l3_activityresult.ui.theme.L3_ActivityResultTheme
import kotlin.reflect.KFunction1

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoList = intent.extras?.getStringArrayList("todoList")?: ArrayList()
        setContent {
            L3_ActivityResultTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemWindow(::doneAdding, todoList)
                }
            }
        }
    }
    fun doneAdding(newTask: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("newTask", newTask)
        setResult(RESULT_OK, intent)
        finish()
    }
}

@Composable
fun ItemWindow(doneAdding: KFunction1<String, Unit>, todoList: ArrayList<String>, modifier: Modifier = Modifier) {
    var nuevaTarea by remember { mutableStateOf("") }
    Column (Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nuevaTarea,
            onValueChange = {nuevaTarea = it},
            label = { Text(text = "Nuevo TODO")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(onClick = {
            doneAdding(nuevaTarea)
        }) {
            Text(text = stringResource(id = R.string.done))
        }

    }
}

