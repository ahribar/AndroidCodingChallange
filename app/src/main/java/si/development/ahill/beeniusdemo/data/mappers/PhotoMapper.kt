package si.development.ahill.beeniusdemo.data.mappers

import si.development.ahill.beeniusdemo.data.models.PhotoEntity
import si.development.ahill.beeniusdemo.data.rest.models.PhotoRestModel

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoMapper {

    fun mapAsEntity(restModel: PhotoRestModel): PhotoEntity =
        PhotoEntity(
            id = restModel.id ?: 0L,
            albumId = restModel.albumId,
            thumbnailUrl = restModel.thumbnailUrl,
            title = restModel.title,
            url = restModel.url
        )
}