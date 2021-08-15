package com.example.android.guesstheword.data

import javax.inject.Inject

class HiltDemoInteractorImpl @Inject constructor() : HiltDemoInteractor {
	override suspend fun getCustomString(): String = "Test data"
}