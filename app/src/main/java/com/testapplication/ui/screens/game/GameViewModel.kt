package com.testapplication.ui.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.testapplication.data.Game
import com.testapplication.data.Repository
import com.testapplication.ui.screens.Arguments
import com.testapplication.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: Repository, savedStateHandle: SavedStateHandle
) :
    BaseViewModel<GameStates, GameIntents>(GameStates.Loading) {

    private val drawId: Int? = savedStateHandle[Arguments.DRAW_ID_ARGUMENT]

    override fun handleIntent(intent: GameIntents) {
        when (intent) {
            GameIntents.LoadData -> loadData()
        }

    }

    private fun loadData() {
        val drawId = drawId ?: return

        viewModelScope.launch {
            val response = repository.getData(drawId)
            val body = response.body()
            _state.value = if (response.isSuccessful && body != null) {
                GameStates.Loaded(body)
            } else {
                GameStates.Error
            }
        }
    }

}

sealed interface GameIntents {
    data object LoadData : GameIntents
}

sealed interface GameStates {
    data object Loading : GameStates
    data object Error : GameStates

    data class Loaded(val data: Game) : GameStates
}
