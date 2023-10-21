package com.example.fordemocleanarch.ui.view.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordemocleanarch.domain.common.base.BaseResult
import com.example.fordemocleanarch.domain.model.Game
import com.example.fordemocleanarch.domain.usecase.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

@HiltViewModel
class GameVIewModel @Inject constructor(
    private val gameUseCase: GetGameUseCase
) : ViewModel() {

   private val _state = MutableStateFlow<GameUiState>(GameUiState.Init)
   val state: StateFlow<GameUiState> get() = _state

   private val _games = MutableStateFlow(mutableListOf<Game>())
   val games: StateFlow<List<Game>> get() = _games

   private fun setLoading() {
      _state.value = GameUiState.IsLoading(true)
   }

   private fun hideLoading() {
      _state.value = GameUiState.IsLoading(false)
   }

   private fun showToast(msg: String) {
      _state.value = GameUiState.ShowToast(msg)
   }

   init {
      fetchGames()
   }

   fun fetchGames() {
      viewModelScope.launch {
         gameUseCase.invoke()
             .onStart {
                setLoading()
             }
             .catch { ex ->
                hideLoading()
                showToast(ex.message.toString())
             }
             .collect { result ->
                hideLoading()
                when (result) {
                   is BaseResult.Error -> {
                      if (result.message.code != 0) {
                         showToast(result.message.message)
                      }
                   }

                   is BaseResult.Success -> {
                      _games.value = result.data as MutableList<Game>
                   }
                }
             }
      }
   }
}

sealed class GameUiState {
   object Init : GameUiState()
   data class IsLoading(val isLoading: Boolean) : GameUiState()
   data class ShowToast(val message: String) : GameUiState()
}