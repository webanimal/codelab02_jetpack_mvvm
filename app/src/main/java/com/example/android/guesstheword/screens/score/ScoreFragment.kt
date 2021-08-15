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

package com.example.android.guesstheword.screens.score

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
import com.example.android.guesstheword.databinding.ScoreFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment where the final score is shown, after the game is over
 */
@AndroidEntryPoint
class ScoreFragment : Fragment() {
	
	private lateinit var binding: ScoreFragmentBinding
	private val viewModel: ScoreViewModel by viewModels()
	private val TAG = ScoreFragment::class.java.simpleName
	
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
		
		binding = ScoreFragmentBinding.inflate(inflater, container, false)
		setupListeners()
		setupViewModel()
		
		return binding.root
	}
	
	override fun onDetach() {
		Log.i(TAG, "LIFECYCLE::onDetach")
		super.onDetach()
	}
	
	private fun playAgain() {
		Log.i(TAG, "LIFECYCLE::playAgain")
		viewModel.onPlayAgainComplete()
		Toast.makeText(activity, "Restart", Toast.LENGTH_SHORT).show()
		findNavController().navigate(ScoreFragmentDirections.actionRestart())
	}
	
	private fun setupListeners() {
		binding.playAgainButton.setOnClickListener { viewModel.onPlayAgain() }
	}
	
	private fun setupViewModel() {
		viewModel.score.observe(viewLifecycleOwner, { finalScore ->
			binding.scoreText.text = finalScore.toString()
		})
		viewModel.eventPlayAgain.observe(viewLifecycleOwner, { isPlayAgain ->
			if (isPlayAgain) playAgain()
		})
	}
}