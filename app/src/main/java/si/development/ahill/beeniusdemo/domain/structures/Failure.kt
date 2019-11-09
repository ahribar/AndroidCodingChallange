package si.development.ahill.beeniusdemo.domain.structures

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
sealed class Failure(exception: Exception = Exception("Failure")) {

    object None : Failure()

    object NetworkConnection : Failure()

    object ServerError : Failure()

    open class FeatureFailure(
        featureException: Exception = Exception("Feature failure")
    ) : Failure(featureException)

    override fun equals(other: Any?): Boolean =
        other is Failure

    override fun hashCode(): Int =
        javaClass.hashCode()
}