package space.lobanov.mvvmkotlintask.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.koin.dsl.module
import space.lobanov.mvvmkotlintask.model.HitData
import space.lobanov.mvvmkotlintask.model.Hits
import java.util.jar.Attributes

@Database(
    entities = [Hits::class],
    version = 1, exportSchema = false
)
abstract class ImageDatabase : RoomDatabase() {

    abstract val imageDao: ImageDao


}