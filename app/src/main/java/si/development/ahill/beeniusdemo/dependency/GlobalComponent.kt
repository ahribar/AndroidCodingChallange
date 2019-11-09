package si.development.ahill.beeniusdemo.dependency

import dagger.Component
import si.development.ahill.beeniusdemo.dependency.modules.DataModule
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
}