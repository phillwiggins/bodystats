package com.purewowstudio.bodystats.domain.stores

import com.purewowstudio.bodystats.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserStore {
    fun getUserDetailsStream(): Flow<User>
    suspend fun setUser(user: User)
}