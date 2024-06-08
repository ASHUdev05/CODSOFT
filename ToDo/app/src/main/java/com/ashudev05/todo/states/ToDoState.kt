package com.ashudev05.todo.states

import java.time.LocalDate

data class ToDoState (
    val todos: Map<String, LocalDate> = mutableMapOf(),
)