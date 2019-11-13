package si.development.ahill.beeniusdemo.utils.structures

/**
 * Created by Andraž Hribar on 13. 11. 2019.
 * andraz.hribar@gmail.com
 */
open class SelfDestructibleMessage<out T>(private val content: T) {

    var isDestroyed = false
        private set // Should be accessible only from this class

    fun read(): T? =
        if (isDestroyed) null
        else content.also { isDestroyed = true }
}
