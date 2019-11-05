package si.development.ahill.beeniusdemo.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "phone")
    val phone: String?,
    @ColumnInfo(name = "username")
    val username: String?,
    @ColumnInfo(name = "website")
    val website: String?,
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "latitude")
    val latitude: String?,
    @ColumnInfo(name = "longitude")
    val longitude: String?,
    @ColumnInfo(name = "street")
    val street: String?,
    @ColumnInfo(name = "suite")
    val suite: String?,
    @ColumnInfo(name = "zipCode")
    val zipCode: String?,
    @ColumnInfo(name = "companyBs")
    val companyBs: String?,
    @ColumnInfo(name = "companyCatchPhrase")
    val companyCatchPhrase: String?,
    @ColumnInfo(name = "companyName")
    val companyName: String?
) : BaseEntity