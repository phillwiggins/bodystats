package com.purewowstudio.bodystats.ui.weight

sealed class WeightScreenView {
    object Loading: WeightScreenView()
    data class Loaded(
        val text: String
    ): WeightScreenView()
    object Error: WeightScreenView()
}