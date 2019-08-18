package ru.mozgovoy.rubetek_test_task.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface DataDao {
    @Query("SELECT * FROM dataentity")
    fun getValue(): Flowable<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: DataEntity)
}