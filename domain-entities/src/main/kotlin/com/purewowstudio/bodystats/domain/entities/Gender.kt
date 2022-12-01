package com.purewowstudio.bodystats.domain.entities

import com.purewowstudio.bodystats.domain.base.R

sealed class Gender(val value: Int, val name: Int) {
    object Male: Gender(value = 0, name = R.string.male)
    object Female: Gender(value = 1, name = R.string.female)
}
