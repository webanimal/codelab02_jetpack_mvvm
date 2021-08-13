package com.example.android.guesstheword.screens.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.guesstheword.screens.game.GameViewModel
import com.example.android.guesstheword.screens.score.ScoreViewModel

class MyViewModelFactory(private val finalScore: Int = -999) : ViewModelProvider.Factory {
	
	private val TAG = MyViewModelFactory::class.java.simpleName
	
	init {
		Log.i(TAG, "LIFECYCLE::init")
	}
	
	@Suppress("Unchecked_Cast")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
		GameViewModel::class.java -> GameViewModel()
		ScoreViewModel::class.java -> ScoreViewModel(finalScore)
		else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
	} as T
}