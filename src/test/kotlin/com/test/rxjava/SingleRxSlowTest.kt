package com.test.rxjava

import io.kotlintest.specs.StringSpec
import io.reactivex.Observable
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers

class SingleRxSlowTest : StringSpec() {
    init {

        "Observable todos can be subscribed" {
            val todoObservable: Observable<Todo> = Observable.fromIterable(TodoMemoryRepository().allTodosSlow())

            System.err.print("Start")
            val disposable = todoObservable

                    .subscribe({ todo ->
                        run {
                            println("Subscriber thread: " + Thread.currentThread().name)
                            println(todo)
                        }
                    }, { e -> e.printStackTrace() })

            disposable.dispose()
        }
    }
}