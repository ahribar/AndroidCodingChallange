package si.development.ahill.beeniusdemo.data.mappers

import si.development.ahill.beeniusdemo.data.models.PhotoEntity
import si.development.ahill.beeniusdemo.data.rest.models.PhotoRestModel
import si.development.ahill.beeniusdemo.domain.mappers.DomainModelMapper
import si.development.ahill.beeniusdemo.domain.models.Photo
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoMapper @Inject constructor() : DataModelMapper<PhotoRestModel, PhotoEntity>,
    DomainModelMapper<PhotoEntity, Photo> {

    override fun mapAsEntity(restModel: PhotoRestModel): PhotoEntity =
        PhotoEntity(
            id = restModel.id ?: 0L,
            albumId = restModel.albumId,
            thumbnailUrl = restModel.thumbnailUrl,
            title = restModel.title,
            url = restModel.url
        )

    override fun mapAsPresentable(entity: PhotoEntity): Photo =
        Photo(
            id = entity.id,
            albumId = entity.albumId,
            thumbnailUrl = entity.thumbnailUrl,
            title = entity.title,
            url = entity.url
        )
}