package ru.mozgovoy.rubetek_test_task.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var value: Int = 0
)