package com.test.rxjava

class TodoMemoryRepository : TodoRepository {
    
    override fun allTodosSlow(): List<Todo> {
        println("loading")
        Thread.sleep(2000)
        val allTodos = allTodos()
        println("end loading")
        return allTodos
    }

    override fun allTodos(): List<Todo> {
        return listOf(Todo("Task1"), Todo("Task2"), Todo("Task3"))
    }
}