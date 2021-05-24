package com.yeocak.tinderfood.model

data class DiscoverCard(

    val photoLink: String,
    val name: String,
    val time: String,
    val serving: String,
    val vegan: VeganType,
    val ingredients: String
)