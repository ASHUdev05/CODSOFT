package com.ashudev05.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashudev05.todo.modals.AddToDoModel
import com.ashudev05.todo.screens.AddToDo
import com.ashudev05.todo.screens.Home
import com.ashudev05.todo.ui.theme.ToDoTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                App()
            }
        }
    }
}

@Composable
fun App(
    todoViewModel: AddToDoModel = viewModel()
) {
    val todoUiState by todoViewModel.toDoState.collectAsState()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController, todoUiState.todos)
        }
        composable("add_todo") {
            AddToDo(navController, todoUiState.todos)
        }
    }
}

@Composable
fun ToDoCard(
    todo: String = "None",
    dueDate: LocalDate = LocalDate.now()
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            disabledElevation = 0.dp,
            pressedElevation = 10.dp,
            focusedElevation = 8.dp,
            hoveredElevation = 6.dp,
            draggedElevation = 12.dp,
        ),
        onClick = { /*TODO*/ }
    ) {
        Text(text = todo)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = dueDate.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ToDoTheme {
        App()
    }
}