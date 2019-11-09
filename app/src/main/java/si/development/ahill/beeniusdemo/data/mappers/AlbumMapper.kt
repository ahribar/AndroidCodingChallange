package si.development.ahill.beeniusdemo.data.mappers

import si.development.ahill.beeniusdemo.data.models.AlbumEntity
import si.development.ahill.beeniusdemo.data.rest.models.AlbumRestModel
import si.development.ahill.beeniusdemo.domain.mappers.DomainModelMapper
import si.development.ahill.beeniusdemo.domain.models.Album
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumMapper @Inject constructor() : DataModelMapper<AlbumRestModel, AlbumEntity>,
    DomainModelMapper<AlbumEntity, Album> {

    override fun mapAsEntity(restModel: AlbumRestModel): AlbumEntity =
        AlbumEntity(
            id = restModel.id ?: 0L,
            title = restModel.title,
            userId = restModel.userId
        )

    override fun mapAsPresentable(entity: AlbumEntity): Album =
        Album(
            id = entity.id,
            title = entity.title,
            userId = entity.userId
        )
}