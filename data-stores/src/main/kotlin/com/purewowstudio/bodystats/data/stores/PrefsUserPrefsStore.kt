package com.purewowstudio.bodystats.data.stores

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.domain.stores.UserPrefsStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PrefsUserPrefsStore @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPrefsStore {

    companion object {
        private const val fileName = "prefs_user_prefs_store"

        private val weightPrefKey = intPreferencesKey("key_weight_type")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = fileName)

    override suspend fun getWeightType(): Weight.Type = context.dataStore.data
        .map { preferences ->
            when (preferences[weightPrefKey]) {
                0 -> Weight.Type.KILOGRAMS
                1 -> Weight.Type.POUNDS
                null -> Weight.Type.KILOGRAMS
                else -> throw IllegalStateException("Weight type stored incorrectly")
            }
        }.first()

    override suspend fun setWeightType(weightType: Weight.Type) {
        context.dataStore.edit { preferences ->
            preferences[weightPrefKey] = when (weightType) {
                Weight.Type.KILOGRAMS -> 0
                Weight.Type.POUNDS -> 1
            }
        }
    }
}