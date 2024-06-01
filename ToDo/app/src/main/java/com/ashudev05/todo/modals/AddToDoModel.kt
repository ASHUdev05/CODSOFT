package com.ashudev05.todo.modals

import androidx.lifecycle.ViewModel

// create a data class to represent the todo item
// A todo has a title, description, and a due date
class AddToDoModel : ViewModel() {
    private var _name: String = ""
    private var _dueDate: String = ""
    private var _todos: MutableList<AddToDoModel> = mutableListOf()

    val name: String = _name
    val dueDate: String = _dueDate
    val todos: List<AddToDoModel> = _todos
}