package com.muvi.navigation

import androidx.lifecycle.Observer

class EventObserver<T>(private val handleEvent: (T) -> Unit) : Observer<EventWrapper<T>> {

    override fun onChanged(eventWrapper: EventWrapper<T>?) {
        eventWrapper?.value()?.let { handleEvent(it) }
    }
}

class EventWrapper<T>(private val value: T) {

    // TODO: atomic boolean
    private var delivered = false

    fun value() = if (delivered) {
        null
    } else {
        delivered = true
        value
    }
}
