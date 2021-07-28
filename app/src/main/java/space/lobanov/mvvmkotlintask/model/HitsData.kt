package space.lobanov.mvvmkotlintask.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.collections.ArrayList

data class HitData(
    @SerializedName("total")
    val totalData: String,
    @SerializedName("hits")
    var listofHits: List<Hits>
)

@Entity(tableName = "todo_items")
@Parcelize
class Hits(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "image")
    @SerializedName("userImageURL")
    val userImageUrl: String,
    @ColumnInfo(name = "userName")
    @SerializedName("user")
    val userName:String

) : Parcelable





