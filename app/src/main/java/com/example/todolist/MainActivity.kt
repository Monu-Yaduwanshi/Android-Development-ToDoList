// only task and time printing
//package com.example.todolist
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.todolist.ui.theme.TodoListTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            TodoListTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ToDoListScreen(modifier = Modifier.padding(innerPadding))
//                }
//            }
//        }
//    }
//}
//
//data class Task(
//    val task_id: Int,
//    val task_name: String,
//    var task_time: String,
//    var isEditing: Boolean = false,
//    var isDeleting: Boolean = false
//)
//
//@Composable
//fun ToDoListScreen(modifier: Modifier = Modifier) {
//    var listOfTasks by remember { mutableStateOf(listOf<Task>()) }
//    var isDialogOpen by remember { mutableStateOf(false) }
//    var taskName by remember { mutableStateOf("") }
//    var taskTime by remember { mutableStateOf("") }
//
//    val context = LocalContext.current
//
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(onClick = { isDialogOpen = true }) {
//            Text(text = "Add Task")
//        }
//
//        // Display the list of tasks after adding
//        LazyColumn(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            items(listOfTasks) { task ->
//                Text(text = "Task: ${task.task_name}, Time: ${task.task_time}")
//            }
//        }
//    }
//
//    if (isDialogOpen) {
//        AlertDialog(
//            onDismissRequest = { isDialogOpen = false },
//            confirmButton = {
//                Button(onClick = {
//                    if (taskName.isNotBlank() && taskTime.isNotBlank()) {
//                        listOfTasks = listOfTasks + Task(
//                            task_id = listOfTasks.size + 1,
//                            task_name = taskName,
//                            task_time = taskTime
//                        )
//                        Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
//                        taskName = ""
//                        taskTime = ""
//                        isDialogOpen = false
//                    }
//                }) {
//                    Text(text = "Submit")
//                }
//            },
//            dismissButton = {
//                Button(onClick = {
//                    Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
//                    isDialogOpen = false
//                }) {
//                    Text(text = "Cancel")
//                }
//            },
//            title = { Text(text = "Task") },
//            text = {
//                Column {
//                    OutlinedTextField(
//                        value = taskName,
//                        onValueChange = { taskName = it },
//                        label = { Text(text = "Enter Name") }
//                    )
//                    OutlinedTextField(
//                        value = taskTime,
//                        onValueChange = { taskTime = it },
//                        label = { Text(text = "Enter Time") }
//                    )
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TodoListTheme {
//        Greeting("Android")
//    }
//}
//
//
//

//with edit and delete option
package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Task(
    var task_id: Int,
    var task_name: String,
    var task_time: String,
    var isEditing: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    TodoListApp() // Calling the main Composable function
                }
            }
        }
    }
}

@Composable
fun TodoListApp() {
    var listOfTask by remember { mutableStateOf(listOf<Task>()) }
    var isDialog by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }
    var taskTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isDialog = true }) {
            Text("Add Task")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(listOfTask) { task ->
                if (task.isEditing) {
                    editTask(task = task) { editedName, editedTime ->
                        listOfTask = listOfTask.map {
                            if (it.task_id == task.task_id) {
                                it.copy(task_name = editedName, task_time = editedTime, isEditing = false)
                            } else {
                                it
                            }
                        }
                    }
                } else {
                    showTaskList(task = task, {
                        // onEdit Click
                        listOfTask = listOfTask.map {
                            it.copy(isEditing = it.task_id == task.task_id)
                        }
                    }, {
                        // onDelete Logic
                        listOfTask = listOfTask - task
                    })
                }
            }
        }
    }

    if (isDialog) {
        AlertDialog(
            onDismissRequest = { isDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = { isDialog = false }) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                        if (taskName.isNotEmpty() && taskTime.isNotEmpty()) {
                            val newTask = Task(
                                task_id = listOfTask.size + 1,
                                task_name = taskName,
                                task_time = taskTime
                            )
                            listOfTask = listOfTask + newTask
                            isDialog = false
                            taskName = ""
                            taskTime = ""
                        }
                    }) {
                        Text("Add")
                    }
                }
            },
            title = { Text(text = "Add Task to Do") },
            text = {
                Column {
                    OutlinedTextField(value = taskName, onValueChange = {
                        taskName = it
                    }, label = { Text("Enter task") })

                    OutlinedTextField(value = taskTime, onValueChange = {
                        taskTime = it
                    }, label = { Text("Enter time") })
                }
            }
        )
    }
}

@Composable
fun editTask(task: Task, onEditComplete: (String, String) -> Unit) {
    var isDialog by remember { mutableStateOf(true) }
    var editedName by remember { mutableStateOf(task.task_name) }
    var editedTime by remember { mutableStateOf(task.task_time) }

    AlertDialog(
        onDismissRequest = { isDialog = false },
        confirmButton = {
            Button(onClick = {
                onEditComplete(editedName, editedTime)
                isDialog = false
            }) {
                Text("Save")
            }
        },
        title = { Text("Editing Task") },
        text = {
            Column {
                OutlinedTextField(value = editedName, onValueChange = {
                    editedName = it
                }, label = { Text("Enter task") })

                OutlinedTextField(value = editedTime, onValueChange = {
                    editedTime = it
                }, label = { Text("Enter time") })
            }
        }
    )
}

@Composable
fun showTaskList(
    task: Task,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                border = BorderStroke(3.dp, Color.Black),
                shape = RoundedCornerShape(20)
            )
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(task.task_name,
                style = TextStyle(
                    fontSize = 30.sp
                )
            )
            Text(task.task_time)
        }
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Task")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}
