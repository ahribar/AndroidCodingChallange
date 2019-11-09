package si.development.ahill.beeniusdemo.global

import android.app.Application
import si.development.ahill.beeniusdemo.dependency.DaggerGlobalComponent
import si.development.ahill.beeniusdemo.dependency.DependencyProvider
import si.development.ahill.beeniusdemo.dependency.modules.DataModule

/**
 * Created by Andraž Hribar on 4. 11. 2019.
 * andraz.hribar@gmail.com
 */
class BeeniusDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    private fun initComponents() {
        DependencyProvider.globalComponent = DaggerGlobalComponent.builder()
            .dataModule(DataModule(this))
            .build()
    }
}