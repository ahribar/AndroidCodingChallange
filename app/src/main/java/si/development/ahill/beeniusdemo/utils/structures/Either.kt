package si.development.ahill.beeniusdemo.utils.structures

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
sealed class Either<out L, out R> {

    data class Left<out L>(
        val left: L
    ) : Either<L, Nothing>()

    data class Right<out R>(
        val right: R
    ) : Either<Nothing, R>()

    val isRight
        get() = this is Right<R>

    val isLeft
        get() = this is Left<L>

    fun <L> left(left: L) =
        Left(left)

    fun <R> right(right: R) =
        Right(right)

    fun either(leftFunction: (L) -> Any, rightFunction: (R) -> Any): Any =
        when (this) {
            is Left -> leftFunction(left)
            is Right -> rightFunction(right)
        }
}