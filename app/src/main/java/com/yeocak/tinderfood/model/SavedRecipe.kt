package com.yeocak.tinderfood.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedRecipe(
    @PrimaryKey
    val uid: Int,
    val name: String
)
