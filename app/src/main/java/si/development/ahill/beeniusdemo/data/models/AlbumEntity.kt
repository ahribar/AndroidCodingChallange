package si.development.ahill.beeniusdemo.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "userId")
    val userId: Long?
) : BaseEntity