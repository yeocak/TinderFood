package com.yeocak.tinderfood.model.recipes

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)