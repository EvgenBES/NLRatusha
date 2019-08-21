package com.blackstone.ratusha.ui.base.recycler

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
abstract class BaseItemViewModel<Entity> {
    abstract fun bindItem(item: Entity, position: Int)
    open fun onItemClick() {}
}