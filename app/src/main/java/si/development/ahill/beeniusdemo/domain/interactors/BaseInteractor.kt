package si.development.ahill.beeniusdemo.domain.interactors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import si.development.ahill.beeniusdemo.utils.structures.Either
import si.development.ahill.beeniusdemo.utils.structures.Failure

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
abstract class BaseInteractor<out Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        params: Params,
        workScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
        returnScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
        onResult: (Either<Failure, Type>) -> Unit
    ) {
        val job = workScope.async { run(params) }
        returnScope.launch { onResult(job.await()) }
    }
}