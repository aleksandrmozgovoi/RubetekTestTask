package ru.mozgovoy.rubetek_test_task.data.room

import io.reactivex.Flowable

interface Database {
    fun insert(value: DataEntity)
    fun getValue(): Flowable<List<DataEntity>>
}