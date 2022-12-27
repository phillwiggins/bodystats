package com.purewowstudio.bodystats.domain.stores

import com.purewowstudio.bodystats.domain.entities.Weight

interface UserPrefsStore {
    suspend fun getWeightType(): Weight.Type
    suspend fun setWeightType(weightType: Weight.Type)
}