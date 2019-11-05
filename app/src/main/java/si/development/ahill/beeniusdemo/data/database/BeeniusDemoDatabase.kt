package si.development.ahill.beeniusdemo.data.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import si.development.ahill.beeniusdemo.data.models.AlbumEntity
import si.development.ahill.beeniusdemo.data.models.PhotoEntity
import si.development.ahill.beeniusdemo.data.models.UserEntity

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Database(
    entities = [
        UserEntity::class,
        AlbumEntity::class,
        PhotoEntity::class
    ],
    version = 1,
    exportSchema = false
)
class BeeniusDemoDatabase : RoomDatabase() {

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}