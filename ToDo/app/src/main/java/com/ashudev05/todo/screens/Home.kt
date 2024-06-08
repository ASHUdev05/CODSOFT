package com.ashudev05.todo.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.ashudev05.todo.R
import com.ashudev05.todo.ToDoCard
import com.ashudev05.todo.ui.theme.ToDoTheme
import java.time.LocalDate
import java.util.logging.Logger

@Composable
fun Home(
    navController: NavController,
    todos: Map<String, LocalDate> = mapOf(),
) {
    ToDoTheme {
        Scaffold (
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate( route = "add_todo" ) },
                    shape = MaterialTheme.shapes.small
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add ToDo"
                    )
                }
            }
        ) {
                paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                Logger.getLogger("Home").info("Todos: $todos")
                items(todos.entries.toList()) { (todo, dueDate) ->
                    ToDoCard(todo, dueDate)
                }
            }
        }
    }
}