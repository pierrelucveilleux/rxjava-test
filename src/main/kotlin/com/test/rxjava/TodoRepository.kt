package com.test.rxjava

interface TodoRepository {

    fun allTodos() : List<Todo>
}