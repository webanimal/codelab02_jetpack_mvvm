package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
	
	var word = ""
	var score = 0
	
	private val TAG = GameViewModel::class.java.simpleName
	private lateinit var wordList: MutableList<String>
	
	init {
		Log.i(TAG, "LIFECYCLE::init")
		resetList()
		nextWord()
	}
	
	override fun onCleared() {
		super.onCleared()
		Log.i(TAG, "LIFECYCLE::onCleared")
	}
	
	// region Methods for buttons presses
	fun onSkip() {
		score--
		nextWord()
	}
	
	fun onCorrect() {
		score++
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
			word = wordList.removeAt(0)
		}
	}
}