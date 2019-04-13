package com.blackstone.ratusha.di.injection

import android.support.v7.widget.LinearLayoutManager
import com.blackstone.ratusha.ui.screens.forpost.FForpost
import dagger.Module
import dagger.Provides

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
@Module
class FragmentModule {
    @Provides
    internal fun provideLinearLayoutManager(fragment: FForpost): LinearLayoutManager = LinearLayoutManager(fragment.activity)
}