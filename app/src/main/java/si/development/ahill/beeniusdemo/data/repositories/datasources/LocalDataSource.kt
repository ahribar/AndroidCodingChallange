package si.development.ahill.beeniusdemo.data.repositories.datasources

import si.development.ahill.beeniusdemo.data.models.BaseEntity

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface LocalDataSource<T : BaseEntity> : DataSource {

    suspend fun syncData(list: List<T>)

    companion object {

        const val EXPIRY_INTERVAL: Long = 30000L
    }
}