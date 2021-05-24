package com.yeocak.tinderfood.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Preference (
    @PrimaryKey
    var preferenceId: Int = 0,
    var veganType: VeganType = VeganType.NONE,
    var cheap: Boolean? = null,
    var dairy: Boolean? = null,
    var gluten: Boolean? = null,
    var healthy: Boolean? = null,
    var popular: Boolean? = null
)