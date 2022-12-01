package com.purewowstudio.bodystats.data.stores

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.purewowstudio.bodystats.domain.entities.Gender
import com.purewowstudio.bodystats.domain.entities.User
import com.purewowstudio.bodystats.domain.stores.UserStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

internal class PrefsUserStore @Inject constructor(
    @ApplicationContext private val context: Context
) : UserStore {

    companion object {
        private const val fileName = "prefs_user_store"

        private val namePrefKey = stringPreferencesKey("key_name")
        private val descriptionPrefKey = stringPreferencesKey("key_description")
        private val avatarPrefKey = stringPreferencesKey("key_avatar")
        private val genderPrefKey = intPreferencesKey("key_gender")
        private val dobPrefKey = longPreferencesKey("key_dob")

        private const val defaultName = "John Smith"
        private const val defaultAvatarUrl = "http://www.gravatar.com/avatar/?d=identicon"
        private const val defaultDescription = ""
        private val defaultDob = LocalDate.of(
            1992,
            Month.JANUARY,
            1
        )
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = fileName)

    override fun getUserDetailsStream(): Flow<User> = context.dataStore.data
        .map { preferences ->

            val genderValue = preferences[genderPrefKey]
            val dobValue = preferences[dobPrefKey]

            User(
                name = preferences[namePrefKey] ?: defaultName,
                gender = when (genderValue) {
                    Gender.Male.value -> Gender.Male
                    else -> Gender.Female
                },
                description = preferences[descriptionPrefKey] ?: defaultDescription,
                avatarUrl = preferences[avatarPrefKey] ?: defaultAvatarUrl,
                dob = if (dobValue != null) LocalDate.ofEpochDay(dobValue) else defaultDob,
            )
        }

    override suspend fun setUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[namePrefKey] = user.name
            preferences[descriptionPrefKey] = user.description
            preferences[avatarPrefKey] = user.avatarUrl
            preferences[genderPrefKey] = user.gender.value
            preferences[dobPrefKey] = user.dob.toEpochDay()
        }
    }
}