package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
class DaoRepositoryImpl @Inject constructor(private val appDataBase: AppDataBase) :
    DaoRepository {


    }