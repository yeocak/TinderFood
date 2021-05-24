package com.yeocak.tinderfood.database

import android.content.Context
import com.yeocak.tinderfood.model.recipes.Recipe

object FoodRepository {
    suspend fun getFood(id: Int, context: Context): Recipe? =
        FoodDatabase.getInstance(context).foodDao().getFood(id)

    suspend fun insertFood(food: Recipe, context: Context) =
        FoodDatabase.getInstance(context).foodDao().insertFood(food)

    suspend fun getAllFoods(context: Context): List<Recipe>? =
        FoodDatabase.getInstance(context).foodDao().getAllFoods()

    suspend fun deleteAll(context: Context) {
        FoodDatabase.getInstance(context).foodDao().deleteAll()
    }
}