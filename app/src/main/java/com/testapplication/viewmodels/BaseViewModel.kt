package com.testapplication.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Intent>(initialValue: State) : ViewModel() {
    protected val _state = MutableStateFlow(initialValue)
    val state: StateFlow<State>
        get() = _state

    abstract fun handleIntent(intent: Intent)
}
