package si.development.ahill.beeniusdemo.dependency.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import si.development.ahill.beeniusdemo.data.database.BeeniusDemoDatabase
import si.development.ahill.beeniusdemo.data.database.daos.AlbumDao
import si.development.ahill.beeniusdemo.data.database.daos.PhotoDao
import si.development.ahill.beeniusdemo.data.database.daos.UserDao
import si.development.ahill.beeniusdemo.data.mappers.AlbumMapper
import si.development.ahill.beeniusdemo.data.mappers.PhotoMapper
import si.development.ahill.beeniusdemo.data.mappers.UserMapper
import si.development.ahill.beeniusdemo.data.repositories.album.AlbumDataRepository
import si.development.ahill.beeniusdemo.data.repositories.album.datasources.AlbumDataSourceLocal
import si.development.ahill.beeniusdemo.data.repositories.album.datasources.AlbumDataSourceRemote
import si.development.ahill.beeniusdemo.data.repositories.photo.PhotoDataRepository
import si.development.ahill.beeniusdemo.data.repositories.photo.datasources.PhotoDataSourceLocal
import si.development.ahill.beeniusdemo.data.repositories.photo.datasources.PhotoDataSourceRemote
import si.development.ahill.beeniusdemo.data.repositories.user.UserDataRepository
import si.development.ahill.beeniusdemo.data.repositories.user.datasources.UserDataSourceLocal
import si.development.ahill.beeniusdemo.data.repositories.user.datasources.UserDataSourceRemote
import si.development.ahill.beeniusdemo.data.rest.BeeniusDemoServiceFactory
import si.development.ahill.beeniusdemo.data.rest.services.AlbumRestService
import si.development.ahill.beeniusdemo.data.rest.services.PhotoRestService
import si.development.ahill.beeniusdemo.data.rest.services.UserRestService
import si.development.ahill.beeniusdemo.domain.repositories.AlbumRepository
import si.development.ahill.beeniusdemo.domain.repositories.PhotoRepository
import si.development.ahill.beeniusdemo.domain.repositories.UserRepository
import javax.inject.Singleton

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Module
class DataModule(
    context: Context
) {

    private var database: BeeniusDemoDatabase =
        BeeniusDemoDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideBeeniusDemoDatabase(): BeeniusDemoDatabase =
        database

    @Provides
    @Singleton
    fun provideUserDao(database: BeeniusDemoDatabase): UserDao =
        database.provideUserDao()

    @Provides
    @Singleton
    fun provideUserRepository(
        localDataSourceLocal: UserDataSourceLocal,
        remoteDataSource: UserDataSourceRemote,
        mapper: UserMapper
    ): UserRepository =
        UserDataRepository(localDataSourceLocal, remoteDataSource, mapper)

    @Provides
    @Singleton
    fun provideUserRestService(serviceFactory: BeeniusDemoServiceFactory): UserRestService =
        serviceFactory.create(UserRestService::class.java)

    @Provides
    @Singleton
    fun provideAlbumDao(database: BeeniusDemoDatabase): AlbumDao =
        database.provideAlbumDao()

    @Provides
    @Singleton
    fun provideAlbumRepository(
        localDataSourceLocal: AlbumDataSourceLocal,
        remoteDataSource: AlbumDataSourceRemote,
        mapper: AlbumMapper
    ): AlbumRepository =
        AlbumDataRepository(localDataSourceLocal, remoteDataSource, mapper)

    @Provides
    @Singleton
    fun provideAlbumRestService(serviceFactory: BeeniusDemoServiceFactory): AlbumRestService =
        serviceFactory.create(AlbumRestService::class.java)

    @Provides
    @Singleton
    fun providePhotoDao(database: BeeniusDemoDatabase): PhotoDao =
        database.providePhotoDao()

    @Provides
    @Singleton
    fun providePhotoRepository(
        localDataSourceLocal: PhotoDataSourceLocal,
        remoteDataSource: PhotoDataSourceRemote,
        mapper: PhotoMapper
    ): PhotoRepository =
        PhotoDataRepository(localDataSourceLocal, remoteDataSource, mapper)

    @Provides
    @Singleton
    fun providePhotoRestService(serviceFactory: BeeniusDemoServiceFactory): PhotoRestService =
        serviceFactory.create(PhotoRestService::class.java)
}