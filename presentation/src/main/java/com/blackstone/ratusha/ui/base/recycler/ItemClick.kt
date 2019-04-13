package com.blackstone.ratusha.ui.base.recycler

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
data class ItemClick<Entity>(val item: Entity,  val position: Int)