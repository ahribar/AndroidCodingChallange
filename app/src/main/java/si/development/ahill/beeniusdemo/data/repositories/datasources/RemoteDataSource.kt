package si.development.ahill.beeniusdemo.data.repositories.datasources

import si.development.ahill.beeniusdemo.data.models.BaseEntity

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface RemoteDataSource<T : BaseEntity> : DataSource {

    override fun isExpired(): Boolean =
        false
}