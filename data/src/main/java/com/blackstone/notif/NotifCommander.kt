package com.blackstone.notif
/**
 * @author Evgeny Butov
 * @created 13.05.2019
 */
interface NotifCommander {
    fun getConfig()
    fun checkTpForpost()
    fun checkTpOctal()
    fun checkOrderForpost()
    fun checkOrderOctal()
}