package com.muvi.core.di

import android.app.Activity
import androidx.fragment.app.Fragment

fun Fragment.coreComponent() = requireActivity().coreComponent()

fun Activity.coreComponent() =
        (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
                ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")
