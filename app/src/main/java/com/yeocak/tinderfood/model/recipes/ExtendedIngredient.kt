package com.yeocak.tinderfood.model.recipes

import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("id")
    val uid: Int,
    val nameClean: String
)