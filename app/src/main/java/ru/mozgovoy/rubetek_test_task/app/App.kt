package ru.mozgovoy.rubetek_test_task.app

import android.app.Application
import ru.mozgovoy.rubetek_test_task.data.room.AppDatabase
import ru.mozgovoy.rubetek_test_task.data.room.DatabaseImpl


class App : Application() {

    companion object {
        lateinit var appDB: DatabaseImpl
    }

    override fun onCreate() {
        super.onCreate()
        appDB = DatabaseImpl(AppDatabase.getDatabase(this))
    }

}