package si.development.ahill.beeniusdemo.data.repositories.photo

import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.repositories.PhotoRepository

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoDataRepository : PhotoRepository {

    override fun getPhotos(): List<Photo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPhotosByAlbumId(albumId: Long): List<Photo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPhotoById(photoId: Long): Photo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}