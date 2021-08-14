package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
	
	val word = MutableLiveData<String>()
	val score = MutableLiveData<Int>()
	
	private val TAG = GameViewModel::class.java.simpleName
	private lateinit var wordList: MutableList<String>
	
	init {
		Log.i(TAG, "LIFECYCLE::init")
		resetList()
		nextWord()
		score.value = 0
	}
	
	override fun onCleared() {
		Log.i(TAG, "LIFECYCLE::onCleared")
		super.onCleared()
	}
	
	// region Methods for buttons presses
	fun onSkip() {
		score.value = (score.value)?.minus(1)
		nextWord()
	}
	
	fun onCorrect() {
		score.value = (score.value)?.plus(1)
		nextWord()
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
			word.value = wordList.removeAt(0)
		}
	}
}