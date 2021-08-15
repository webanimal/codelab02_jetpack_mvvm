package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
	
	private val _currentCountdownTime = MutableLiveData<Long>()
	private val currentCountdownTime: LiveData<Long>
		get() = _currentCountdownTime
	val currentCountdownTimeString = Transformations.map(currentCountdownTime) { time ->
		DateUtils.formatElapsedTime(time)
	}
	
	private val TAG = GameViewModel::class.java.simpleName
	private lateinit var countDownTimer: CountDownTimer
	private lateinit var wordList: MutableList<String>
	
	init {
		Log.i(TAG, "LIFECYCLE::init")
		initCountdownTimer()
		resetList()
		nextWord()
		_score.value = 0
	}
	
	override fun onCleared() {
		Log.i(TAG, "LIFECYCLE::onCleared")
		countDownTimer.cancel()
		
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
	 * Moves to the next word in the list
	 */
	private fun nextWord() {
		if (wordList.isNotEmpty()) {
			//Select and remove a word from the list
			_word.value = wordList.removeAt(0)
			
		} else {
			resetList()
		}
	}
	
	private fun onGameFinish() {
		_eventGameFinished.value = true
	}
	
	private fun initCountdownTimer() {
		countDownTimer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
			override fun onTick(millisUntilFinished: Long) {
				_currentCountdownTime.value = millisUntilFinished / ONE_SECOND
			}
			
			override fun onFinish() {
				_currentCountdownTime.value = DONE
				onGameFinish()
			}
		}
		countDownTimer.start()
	}
	
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
	
	companion object {
		// Time when the game is over
		private const val DONE = 0L
		
		// Countdown time interval
		private const val ONE_SECOND = 1000L
		
		// Total time for the game
		private const val COUNTDOWN_TIME = 15000L
	}
}