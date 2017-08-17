package com.test.rxjava

class TodoMemoryRepository : TodoRepository {
    
    override fun allTodos(): List<Todo> {
        return listOf(Todo("Task1"), Todo("Task2"), Todo("Task3"))
    }
}