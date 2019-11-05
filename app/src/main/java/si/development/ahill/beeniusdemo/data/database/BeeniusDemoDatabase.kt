package si.development.ahill.beeniusdemo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import si.development.ahill.beeniusdemo.data.database.daos.AlbumDao
import si.development.ahill.beeniusdemo.data.database.daos.PhotoDao
import si.development.ahill.beeniusdemo.data.database.daos.UserDao
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
abstract class BeeniusDemoDatabase : RoomDatabase() {

    abstract fun provideUserDao(): UserDao
    abstract fun provideAlbumDao(): AlbumDao
    abstract fun providePhotoDao(): PhotoDao

    companion object {

        private const val DATABASE_NAME = "BeeniusDemoDatabase"

        @Volatile
        private var instance: BeeniusDemoDatabase? = null

        fun getInstance(context: Context): BeeniusDemoDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context, BeeniusDemoDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }

        fun destroyInstance() {
            instance = null
        }
    }
}