package com.blackstone.domain.usecases

import com.blackstone.domain.entity.Config
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.DaoRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.05.2019
 */
class GetConfigUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                           private val itemRepository: DaoRepository
) : BaseUseCase(postExecutionThread) {

    fun getConfig(): Flowable<Config> {
        return itemRepository
            .getConfig()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun update(config: Config): Completable {
        return itemRepository
            .getUpdateConfig(config)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}