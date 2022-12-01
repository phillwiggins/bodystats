package com.purewowstudio.bodystats.domain.stores

import com.purewowstudio.bodystats.domain.entities.NutritionGoal
import com.purewowstudio.bodystats.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface NutritionGoalStore {
    fun getNutritionGoalStream(): Flow<NutritionGoal>
    suspend fun setNutritionGoal(nutritionGoal: NutritionGoal)
}