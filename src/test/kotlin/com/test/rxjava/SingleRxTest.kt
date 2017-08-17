package com.test.rxjava

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

class SingleRxTest : StringSpec() {
    init {

        "Hello observed returns Hello" {
            var result = ""

            val observer = Observable.just("Hello") // provides datea
            observer.subscribe({ s -> result = s }) // Callable as subscriber

            result shouldBe "Hello"
        }

        "Observable todos can be subscribed" {
            val todoObservable: Observable<Todo> = Observable.create({ emitter ->
                try {
                    TodoMemoryRepository().allTodos().forEach { emitter.onNext(it) }
                    emitter.onError(IllegalArgumentException("bad permissions"))
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            })

            val disposable = todoObservable.subscribe({ todo -> println(todo)}, {e -> e.printStackTrace()})
            disposable.dispose()
        }

        "Observable todos can be subscribed with" {
            val todoObservable: Observable<Todo> = Observable.create({ emitter ->
                try {
                    TodoMemoryRepository().allTodos().forEach { emitter.onNext(it) }
                    emitter.onError(IllegalArgumentException("bad permissions"))
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            })

            val disposable = todoObservable.subscribeWith(object : DisposableObserver<Todo>() {
                            override fun onNext(t: Todo) {
                                println("Desciption is ${t.description}")
                            }

                            override fun onError(e: Throwable) {
                                println("An error occurs : ${e.message}")
                            }

                            override fun onComplete() {
                                println("Already done!")
                            }
                        })

            disposable.dispose()
        }
    }
}