package com.yeocak.tinderfood.model.recipes

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)