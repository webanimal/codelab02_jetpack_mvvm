package com.example.android.guesstheword.di

import com.example.android.guesstheword.data.HiltDemoInteractor
import com.example.android.guesstheword.data.HiltDemoInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
	
	@Binds
	abstract fun bindHiltDemoInteractor(impl: HiltDemoInteractorImpl): HiltDemoInteractor
}