package com.yeocak.tinderfood.utils

import android.util.Log
import androidx.room.TypeConverter
import com.yeocak.tinderfood.model.recipes.AnalyzedInstruction
import com.yeocak.tinderfood.model.recipes.ExtendedIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodRoomTypeConverter {

    @TypeConverter
    fun analyzedInstructionToJson(item: List<AnalyzedInstruction>): String {
        val gson = Gson()
        val type = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return gson.toJson(item, type)
    }

    @TypeConverter
    fun jsonToAnalyzedInstruction(item: String): List<AnalyzedInstruction> {
        val gson = Gson()
        val type = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return gson.fromJson(item, type)
    }

    @TypeConverter
    fun extendedIngredientsToJson(item: List<ExtendedIngredient>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return gson.toJson(item, type)
    }


    @TypeConverter
    fun jsonToExtendedIngredients(item: String): List<ExtendedIngredient> {
        val gson = Gson()
        val type = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return gson.fromJson(item, type)
    }
}