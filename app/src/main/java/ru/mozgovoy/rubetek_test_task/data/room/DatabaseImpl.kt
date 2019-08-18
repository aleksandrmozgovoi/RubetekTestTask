package ru.mozgovoy.rubetek_test_task.data.room

import io.reactivex.Flowable

class DatabaseImpl(db_: AppDatabase): Database {
    private val db: AppDatabase = db_

    override fun insert(value: DataEntity) {
        db.dataDao().insert(value)
    }

    override fun getValue(): Flowable<List<DataEntity>> {
        return db.dataDao().getValue()
    }
}