package si.development.ahill.beeniusdemo.dependency

import dagger.Component
import si.development.ahill.beeniusdemo.dependency.modules.DataModule
import si.development.ahill.beeniusdemo.presentation.albums.AlbumsPresenter
import si.development.ahill.beeniusdemo.presentation.photodetails.PhotoDetailsPresenter
import si.development.ahill.beeniusdemo.presentation.photos.PhotosPresenter
import si.development.ahill.beeniusdemo.presentation.users.UsersPresenter
import javax.inject.Singleton

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Singleton
@Component(modules = [DataModule::class])
interface GlobalComponent {

    fun inject(target: UsersPresenter)

    fun inject(target: AlbumsPresenter)

    fun inject(target: PhotosPresenter)

    fun inject(target: PhotoDetailsPresenter)
}