package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
	
	val score = finalScore
	
	private val TAG = ScoreViewModel::class.java.simpleName
	
	init {
		Log.i(TAG, "LIFECYCLE::init finalScore:$score")
	}
	
	override fun onCleared() {
		Log.i(TAG, "LIFECYCLE::onCleared")
		super.onCleared()
	}
}