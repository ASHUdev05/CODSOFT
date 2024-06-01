package com.ashudev05.todo.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ashudev05.todo.R
import com.ashudev05.todo.components.TableRow
import com.ashudev05.todo.components.UnstyledTextField
import com.ashudev05.todo.modals.AddToDoModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDo(
    navController: NavController
) {
    var todo by remember { mutableStateOf("") }
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMM yyyy", Locale.ENGLISH)
                .format(pickedDate)
        }
    }
    val ctx = LocalContext.current

    val vm by viewModel<AddToDoModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add ToDo",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(16.dp),
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                        contentDescription = "Back",
                        modifier = Modifier.clickable { navController.popBackStack() },
                    )
                },
            )
        },
         content = { it ->
//             Column(
//                modifier = Modifier.padding(it),
//            ) {
                 Column(
                     modifier = Modifier
                         .fillMaxSize()
                         .padding(it),
                     horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                 ) {
                 TableRow(
                     label = "Note",
                     color = MaterialTheme.colorScheme.onSecondaryContainer,
                     modifier = Modifier.padding(16.dp),
                 ) {
                     UnstyledTextField(
                         value = todo,
                         onValueChange = { todo = it },
                         placeholder = { Text("Leave a note") },
                         arrangement = Arrangement.End,
                         modifier = Modifier.fillMaxWidth(),
                         textStyle = TextStyle(
                             textAlign = TextAlign.Right
                         ),
                     )
                 }
                     Spacer(modifier = Modifier.height(42.dp))
                     // DatePicker
                     val dateDialogState = rememberMaterialDialogState()
                     Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                     ) {
                         Button(onClick = {
                                dateDialogState.show()
                         }) {
                             Text(text = "Select Date")
                         }
//                         Spacer(modifier = Modifier.width(74.dp))
                         Text(
                             text = formattedDate,
                             style = MaterialTheme.typography.bodyMedium,
                             modifier = Modifier.padding(start = 16.dp),
                         )
                     }
                 Button(
                     onClick = {
                            // Add the todo to the list
                            // and navigate back to the home screen
                               // use AddToDoModel to add the todo
                         vm.todos.add

                         navController.popBackStack())

                     },
                     modifier = Modifier
                         .padding(top = 120.dp)
                         .align(Alignment.CenterHorizontally),
                 ) {
                     Text(text = "Submit")
                 }
                     MaterialDialog(
                         dialogState = dateDialogState,
                         buttons = {
                             positiveButton("OK") {
                                 Toast.makeText(
                                        ctx,
                                        "Selected date: $pickedDate",
                                        Toast.LENGTH_SHORT
                                    ).show()
                             }
                            negativeButton("Cancel") {
                                Toast.makeText(
                                    ctx,
                                    "Cancelled",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                         }
                     ) {
                        datepicker(
                            initialDate = LocalDate.now(),
                            title = "Select Date",
                        ){
                            pickedDate = it
                        }
                     }
                 }
//            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddToDoPreview() {
    AddToDo(navController = rememberNavController())
}