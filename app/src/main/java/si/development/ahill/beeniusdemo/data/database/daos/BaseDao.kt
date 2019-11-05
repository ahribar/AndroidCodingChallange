package si.development.ahill.beeniusdemo.data.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import si.development.ahill.beeniusdemo.data.models.BaseEntity

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
internal interface BaseDao<T : BaseEntity> {

    //region Default Room Annotated Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)

    //endregion Default Room Annotated Methods

    //region Additional CRUD Interface Methods

    // These need to be overridden in implementing abstract DAO classes and annotated with Room's
    // @Query annotation with the SQL statement attached in the comment

    /**
     * Annotate the implementing method with @Query("DELETE FROM <table_name>") and replace
     * <table_name> with the actual table name.
     */
    suspend fun deleteAll()

    /**
     * Annotate the implementing method with @Query("SELECT * FROM <table_name> WHERE id = :id")
     * and replace <table_name> with the actual table name.
     */
//    suspend fun fetchById(id: Long): T?

    /**
     * Annotate the implementing method with @Query("SELECT * FROM <table_name>") and replace
     * <table_name> with the actual table name.
     */
//    suspend fun fetchAll(): List<T>

    //endregion Additional CRUD Interface Methods
}