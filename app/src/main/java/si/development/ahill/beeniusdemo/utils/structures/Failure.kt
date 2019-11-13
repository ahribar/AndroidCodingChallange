package si.development.ahill.beeniusdemo.utils.structures

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
sealed class Failure(open val exception: Exception = Exception()) {

    open class FeatureFailure(
        featureException: Exception = Exception()
    ) : Failure(featureException)

    override fun equals(other: Any?): Boolean =
        other is Failure

    override fun hashCode(): Int =
        javaClass.hashCode()
}