package com.ashudev05.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashudev05.todo.screens.AddToDo
import com.ashudev05.todo.screens.Home
import com.ashudev05.todo.ui.theme.ToDoTheme

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
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController)
        }
        composable("add_todo") {
            AddToDo(navController)
        }
    }
}

@Composable
fun ToDoCard() {
    Card(onClick = { /*TODO*/ }) {
        Text(text = "Hello, World!")
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ToDoTheme {
        App()
    }
}