package com.ashudev05.todo.modals

import androidx.lifecycle.ViewModel
import com.ashudev05.todo.states.ToDoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class AddToDoModel: ViewModel() {
    private val _todoState = MutableStateFlow(ToDoState())
    val toDoState: StateFlow<ToDoState> = _todoState.asStateFlow()

    private lateinit var _todo: String
    private lateinit var _dueDate: LocalDate

    fun setToDo(todo: String) {
        _todo = todo
    }

    fun setDueDate(dueDate: LocalDate) {
        _dueDate = dueDate
    }

    fun addToDo() {
        val currentState = _todoState.value
        _todoState.value = currentState.copy(
            todos = currentState.todos + (_todo to _dueDate)
        )
    }

    fun clearToDo() {
        _todoState.value = ToDoState()
    }

    fun getToDoState(): ToDoState {
        return _todoState.value
    }
}