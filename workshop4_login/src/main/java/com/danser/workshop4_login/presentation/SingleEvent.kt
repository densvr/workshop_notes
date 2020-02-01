package com.danser.workshop4_login.presentation

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class SingleEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? = when {
        hasBeenHandled -> null
        else -> content.apply { hasBeenHandled = true }
    }

    /**
     * Returns the content, even if it's already been handled.
    */
    fun peekContent(): T = content
}
