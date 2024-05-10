package com.testapplication.ui.screens.results

import androidx.lifecycle.viewModelScope
import com.testapplication.data.Draws
import com.testapplication.data.Repository
import com.testapplication.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<ResultsStates, ResultsIntents>(ResultsStates.Loading) {
    override fun handleIntent(intent: ResultsIntents) {
        when (intent) {
            ResultsIntents.LoadData -> loadData()
        }

    }

    private fun loadData() {
        viewModelScope.launch {
            val response = repository.getResults()
            val body = response.body()
            _state.value = if (response.isSuccessful && body != null) {
                ResultsStates.Loaded(body)
            } else {
                ResultsStates.Error
            }
        }
    }

}

sealed interface ResultsIntents {
    data object LoadData : ResultsIntents
}

sealed interface ResultsStates {
    data object Loading : ResultsStates
    data object Error : ResultsStates

    data class Loaded(val data: Draws) : ResultsStates
}
