package com.example.fordemocleanarch.ui.view.game_list

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.fordemocleanarch.databinding.ActivityGameListBinding
import com.example.fordemocleanarch.domain.model.Game
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class GameListActivity : AppCompatActivity() {

   private val binding by lazy { ActivityGameListBinding.inflate(layoutInflater) }
   private val viewModel by viewModels<GameVIewModel>()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      setupAdapter()
      observe()

   }

   private fun observe() {
      observeState()
      observeGame()
   }

   private fun observeGame() {
      viewModel.games.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
          .onEach { handleGame(it) }
          .launchIn(lifecycleScope)
   }

   private fun handleGame(gameList: List<Game>) {
      binding.gameAdapterRv.adapter?.let { data ->
         if (data is GameAdapter) {
            data.submitList(gameList)
         }
      }
   }

   private fun observeState() {
      viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
          .onEach { handleState(it) }
          .launchIn(lifecycleScope)
   }

   private fun handleState(state: GameUiState) {
      when (state) {
         GameUiState.Init -> Unit
         is GameUiState.IsLoading -> handleLoading(state.isLoading)
         is GameUiState.ShowToast -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
      }
   }

   private fun handleLoading(loading: Boolean) {
      if (loading) {
         binding.loadingBar.isVisible = true
      } else {
         binding.loadingBar.isVisible = false
      }
   }

   private fun setupAdapter() {
      binding.gameAdapterRv.apply {
         adapter = GameAdapter()
      }
   }
}