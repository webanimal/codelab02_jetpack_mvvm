package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
	
	private val _word = MutableLiveData<String>()
	val word: LiveData<String>
		get() = _word
	
	private val _score = MutableLiveData<Int>()
	val score: LiveData<Int>
		get() = _score
	
	private val _eventGameFinished = MutableLiveData<Boolean>()
	val eventGameFinished: LiveData<Boolean>
		get() = _eventGameFinished
	
	private val TAG = GameViewModel::class.java.simpleName
	private lateinit var wordList: MutableList<String>
	
	init {
		Log.i(TAG, "LIFECYCLE::init")
		resetList()
		nextWord()
		_score.value = 0
	}
	
	override fun onCleared() {
		Log.i(TAG, "LIFECYCLE::onCleared")
		super.onCleared()
	}
	
	// region Methods for buttons presses
	fun onSkip() {
		_score.value = (_score.value)?.minus(1)
		nextWord()
	}
	
	fun onCorrect() {
		_score.value = (_score.value)?.plus(1)
		nextWord()
	}
	
	fun onGameFinishComplete() {
		_eventGameFinished.value = false
	}
	// endregion
	
	/**
	 * Resets the list of words and randomizes the order
	 */
	private fun resetList() {
		wordList = mutableListOf(
			"queen",
			"hospital",
			"basketball",
			"cat",
			"change",
			"snail",
			"soup",
			"calendar",
			"sad",
			"desk",
			"guitar",
			"home",
			"railway",
			"zebra",
			"jelly",
			"car",
			"crow",
			"trade",
			"bag",
			"roll",
			"bubble"
		)
		wordList.shuffle()
	}
	
	/**
	 * Moves to the next word in the list
	 */
	private fun nextWord() {
		if (!wordList.isEmpty()) {
			//Select and remove a word from the list
			_word.value = wordList.removeAt(0)
			
		} else {
			onGameFinished()
		}
	}
	
	private fun onGameFinished() {
		_eventGameFinished.value = true
	}
}