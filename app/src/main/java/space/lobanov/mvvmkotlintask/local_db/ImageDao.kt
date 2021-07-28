package space.lobanov.mvvmkotlintask.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.DELETE
import space.lobanov.mvvmkotlintask.model.Hits

@Dao
interface ImageDao {
    @Query("SELECT * FROM todo_items")
    fun getAll(): List<Hits>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: List<Hits>)


}