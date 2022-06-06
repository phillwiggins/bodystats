package com.purewowstudio.bodystats.domain.entities

import com.purewowstudio.bodystats.domain.base.R

sealed class Gender(val name: Int) {
    object Male: Gender(name = R.string.male)
    object Female: Gender(name = R.string.female)
}
