package com.example.fox.ratusha.di.injection

import android.support.v7.widget.LinearLayoutManager
import com.example.fox.ratusha.ui.screens.forpost.FForpost
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