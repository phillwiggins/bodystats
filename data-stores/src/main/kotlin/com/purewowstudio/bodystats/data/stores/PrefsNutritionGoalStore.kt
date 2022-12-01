package com.purewowstudio.bodystats.data.stores

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.purewowstudio.bodystats.domain.entities.NutritionGoal
import com.purewowstudio.bodystats.domain.stores.NutritionGoalStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PrefsNutritionGoalStore @Inject constructor(
    @ApplicationContext private val context: Context
) : NutritionGoalStore {

    companion object {
        private const val fileName = "prefs_nutrition_goal_store"

        private val caloriesPrefKey = intPreferencesKey("key_calories")
        private val proteinPrefKey = intPreferencesKey("key_protein")
        private val carbsPrefKey = intPreferencesKey("key_carbs")
        private val fatPrefKey = intPreferencesKey("key_fat")

        private const val defaultCalories = 2000
        private const val defaultProteinPercent = 40
        private const val defaultCarbPercent = 40
        private const val defaultFatPercent = 20
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = fileName)

    override fun getNutritionGoalStream(): Flow<NutritionGoal> = context.dataStore.data
        .map { preferences ->
            NutritionGoal(
                calories = preferences[caloriesPrefKey] ?: defaultCalories,
                proteinPercent = preferences[proteinPrefKey] ?: defaultProteinPercent,
                carbPercent = preferences[carbsPrefKey] ?: defaultCarbPercent,
                fatPercent = preferences[fatPrefKey] ?: defaultFatPercent,
            )
        }

    override suspend fun setNutritionGoal(nutritionGoal: NutritionGoal) {
        context.dataStore.edit { preferences ->
            preferences[caloriesPrefKey] = nutritionGoal.calories
            preferences[proteinPrefKey] = nutritionGoal.proteinPercent
            preferences[carbsPrefKey] = nutritionGoal.carbPercent
            preferences[fatPrefKey] = nutritionGoal.fatPercent
        }
    }
}