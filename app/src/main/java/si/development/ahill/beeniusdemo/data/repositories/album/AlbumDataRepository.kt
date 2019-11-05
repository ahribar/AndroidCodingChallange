package si.development.ahill.beeniusdemo.data.repositories.album

import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.repositories.AlbumRepository

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumDataRepository : AlbumRepository {

    override fun getAlbumsByUserId(userId: Long): List<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumById(albumId: Long): Album? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}