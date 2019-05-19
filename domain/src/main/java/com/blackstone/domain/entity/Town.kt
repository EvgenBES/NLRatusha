package com.blackstone.domain.entity

/**
 * @author Evgeny Butov
 * @created 13.05.2019
 */
enum class Town {
    FORPOST { override fun getId() = 1 },
    OCTAL { override fun getId() = 2 };

    abstract fun getId(): Int
}