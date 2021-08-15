package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.data.HiltDemoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
	// This is from previous fragment, which is navigated from
	private val savedStateHandle: SavedStateHandle,
	private val interactor: HiltDemoInteractor
) : ViewModel() {
	
	private val _score = MutableLiveData<Int>()
	val score: LiveData<Int>
		get() = _score
	
	private val _eventPlayAgain = MutableLiveData<Boolean>()
	val eventPlayAgain: LiveData<Boolean>
		get() = _eventPlayAgain
	
	private val TAG = ScoreViewModel::class.java.simpleName
	
	init {
		val finalScore = savedStateHandle.get<Int>("score") ?: -1
		Log.i(TAG, "LIFECYCLE::init finalScore:$finalScore")
		_score.value = finalScore
	}
	
	override fun onCleared() {
		Log.i(TAG, "LIFECYCLE::onCleared")
		super.onCleared()
	}
	
	fun onPlayAgain() {
		_eventPlayAgain.value = true
	}
	
	fun onPlayAgainComplete() {
		_eventPlayAgain.value = false
	}
}