package com.ashudev05.todo.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ashudev05.todo.R
import com.ashudev05.todo.ToDoCard
import com.ashudev05.todo.ui.theme.ToDoTheme

@Composable
fun Home(
    navController: NavController
) {
    ToDoTheme {
        Scaffold (
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("add_todo") },
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
                items(10) {
                    ToDoCard()
                }
            }
        }
    }
}