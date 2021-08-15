/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.databinding.GameFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment where the game is played
 */
@AndroidEntryPoint
class GameFragment : Fragment() {
	
	private lateinit var binding: GameFragmentBinding
	private val viewModel: GameViewModel by viewModels()
	private val TAG = GameFragment::class.java.simpleName
	
	override fun onAttach(context: Context) {
		Log.i(TAG, "LIFECYCLE::onAttach")
		super.onAttach(context)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		Log.i(TAG, "LIFECYCLE::onCreateView")
		
		binding = GameFragmentBinding.inflate(inflater, container, false)
		setupViewModel()
		setupListeners()
		
		return binding.root
	}
	
	override fun onDetach() {
		Log.i(TAG, "LIFECYCLE::onDetach")
		super.onDetach()
	}
	
	private fun onSkip() {
		viewModel.onSkip()
	}
	
	private fun onCorrect() {
		viewModel.onCorrect()
	}
	
	private fun onEndGame() {
		finishGame()
	}
	
	private fun finishGame() {
		Log.i(TAG, "LIFECYCLE::finishGame finalScore:${viewModel.score}")
		viewModel.onGameFinishComplete()
		Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
		GameFragmentDirections.actionGameToScore(score = viewModel.score.value ?: 0).let {
			findNavController().navigate(it)
		}
	}
	
	private fun setupViewModel() {
		viewModel.score.observe(viewLifecycleOwner, { newScore ->
			binding.scoreText.text = newScore.toString()
		})
		viewModel.word.observe(viewLifecycleOwner, { newWord ->
			binding.wordText.text = newWord
		})
		viewModel.eventGameFinished.observe(viewLifecycleOwner, { isFinished ->
			if (isFinished) finishGame()
		})
		viewModel.currentCountdownTimeString?.observe(viewLifecycleOwner, { countDownTimer ->
			binding.timerText.text = countDownTimer
		})
	}
	
	private fun setupListeners() {
		binding.skipButton.setOnClickListener { onSkip() }
		binding.correctButton.setOnClickListener { onCorrect() }
		binding.endGameButton.setOnClickListener { onEndGame() }
	}
}