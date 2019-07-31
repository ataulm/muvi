package com.muvi.core.di

import android.app.Activity
import androidx.fragment.app.Fragment

fun Fragment.appComponent() = requireActivity().appComponent()

fun Activity.appComponent() =
        (applicationContext as? AppComponentProvider)?.provideAppComponent()
                ?: throw IllegalStateException("AppComponentProvider not implemented: $applicationContext")
