package com.purewowstudio.bodystats.ui.sleep

sealed class SleepScreenView {
    object Loading: SleepScreenView()
    data class Loaded(
        val startTime: String,
        val endTime: String,
        val duration: String,
        val awakeTime: String,
        val lightTime: String,
        val remTime: String,
        val deepTime: String,
        val outOfBedTime: String
    ): SleepScreenView()
    object Error: SleepScreenView()
}