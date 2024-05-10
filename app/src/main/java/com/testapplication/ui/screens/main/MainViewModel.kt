package com.testapplication.ui.screens.main

import androidx.lifecycle.viewModelScope
import com.testapplication.data.Game
import com.testapplication.data.Repository
import com.testapplication.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<MainStates, MainIntents>(MainStates.Loading) {
    override fun handleIntent(intent: MainIntents) {
        when (intent) {
            MainIntents.LoadData -> loadData()
        }

    }

    private fun loadData() {
        viewModelScope.launch {
            val response = repository.getUpcoming()
            val body = response.body()
            _state.value = if (response.isSuccessful && body != null) {
                MainStates.Loaded(body)
            } else {
                MainStates.Error
            }
        }
    }

}

sealed interface MainIntents {
    data object LoadData : MainIntents
}

sealed interface MainStates {
    data object Loading : MainStates
    data object Error : MainStates

    data class Loaded(val data: List<Game>) : MainStates
}
