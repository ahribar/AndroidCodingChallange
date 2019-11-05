package si.development.ahill.beeniusdemo.data.mappers

import si.development.ahill.beeniusdemo.data.models.AlbumEntity
import si.development.ahill.beeniusdemo.data.rest.models.AlbumRestModel

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumMapper {

    fun mapAsEntity(restModel: AlbumRestModel): AlbumEntity =
        AlbumEntity(
            id = restModel.id ?: 0L,
            title = restModel.title,
            userId = restModel.userId
        )
}