package com.example.l3_activityresult

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l3_activityresult.ui.theme.L3_ActivityResultTheme
import androidx.compose.foundation.lazy.items
class MainActivity : ComponentActivity() {
    val appViewModel by viewModels<AppViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoList = intent.extras?.getStringArrayList("todoList")?: appViewModel.todoList
        appViewModel.todoList = todoList
        setContent {
            L3_ActivityResultTheme {
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
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoList(activity: ComponentActivity, viewModel: AppViewModel, modifier: Modifier = Modifier) {
    val forceRefresh by viewModel.forceRefresh.collectAsState()

    // Actualiza la lista cuando forceRefresh cambia
    if (forceRefresh) {
        // Lógica para recargar la lista o realizar cualquier otra acción necesaria
        viewModel.refreshComplete() // Marcar como completado después de actualizar
    }

    Column (
        Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.Start),
            fontSize = 25.sp,
            color = Color.White
        )
        Text(
            text = stringResource(id = R.string.eraseElement),
            modifier = modifier
                .padding(16.dp, 0.dp, 16.dp, 25.dp)
                .align(Alignment.Start),
            fontSize = 12.sp,
            color = Color.White
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.wrapContentSize()
        ) {
            items(viewModel.todoList){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier.wrapContentSize()
                ){

                    Divider(color = Color.LightGray)
                    Text(
                        text = it,
                        modifier = modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    // Regular
                                },
                                onLongClick = {
                                    viewModel.todoList.remove(it)
                                    viewModel.triggerRefresh()
                                })
                            .padding(16.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        color = Color.White
                    )

                }
            }

        }
        Divider(color = Color.White, thickness = 4.dp)
        Button(
            onClick = {
                val intent = Intent(activity, MainActivity2::class.java)
                intent.putExtra("todoList", viewModel.todoList)
                activity.startActivity(intent)
            },
            Modifier.padding(25.dp)
        ) {
            Text(text = stringResource(id = R.string.add_task))
        }

    }
}

