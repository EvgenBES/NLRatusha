package com.blackstone.domain.usecases.base


import com.blackstone.domain.extension.mapErrorException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


abstract class BaseUseCaseParams<Params, T> {

    private var parentJob: Job = SupervisorJob()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(params: Params): T

    fun execute(params: Params, block: CompletionBlock<T>) {
        val response = BaseUseCase.Request<T>().apply { block() }
        unsubscribe()

        parentJob = SupervisorJob()
        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground(params)
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                val error = mapErrorException(e)
                response(error)
            }
        }
    }

    private fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}

