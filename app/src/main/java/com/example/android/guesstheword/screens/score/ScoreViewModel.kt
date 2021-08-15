package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
	
	private val _score = MutableLiveData<Int>()
	val score: LiveData<Int>
		get() = _score
	
	private val TAG = ScoreViewModel::class.java.simpleName
	
	init {
		Log.i(TAG, "LIFECYCLE::init finalScore:$finalScore")
		_score.value = finalScore
	}
	
	override fun onCleared() {
		Log.i(TAG, "LIFECYCLE::onCleared")
		super.onCleared()
	}
}