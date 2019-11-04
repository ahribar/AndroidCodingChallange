package si.development.ahill.beeniusdemo.global

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import si.development.ahill.beeniusdemo.data.rest.BeeniusDemoServiceFactory
import si.development.ahill.beeniusdemo.data.rest.services.UserRestService

/**
 * Created by Andraž Hribar on 4. 11. 2019.
 * andraz.hribar@gmail.com
 */
class BeeniusDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(IO).launch {
            BeeniusDemoServiceFactory().create(UserRestService::class.java).getUsers()
        }
    }
}