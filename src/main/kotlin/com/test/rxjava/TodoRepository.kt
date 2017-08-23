package com.test.rxjava

interface TodoRepository {

    fun allTodos() : List<Todo>

    fun allTodosSlow() : List<Todo>
}