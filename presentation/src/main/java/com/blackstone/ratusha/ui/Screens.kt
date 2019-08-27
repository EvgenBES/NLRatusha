package com.blackstone.ratusha.ui

import androidx.fragment.app.Fragment
import com.blackstone.ratusha.ui.screens.forpost.FForpost
import com.blackstone.ratusha.ui.screens.information.FInformation
import com.blackstone.ratusha.ui.screens.main.FMain
import com.blackstone.ratusha.ui.screens.octal.FOctal

/**
 * @author Evgeny Butov
 * @created 27.08.2019
 */
object Screens {

    const val HOME = "HOME"
    const val FORPOST = "FORPOST"
    const val OCTAL = "OCTAL"
    const val INFO = "INFO"

    fun tabScreen(tab: String): Fragment {
        return when (tab) {
            FORPOST -> FForpost()
            OCTAL -> FOctal()
            INFO -> FInformation()
            else -> FMain()
        }
    }

}