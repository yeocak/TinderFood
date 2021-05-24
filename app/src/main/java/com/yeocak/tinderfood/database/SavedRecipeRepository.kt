package com.yeocak.tinderfood.database

import android.content.Context
import com.yeocak.tinderfood.model.SavedRecipe

object SavedRecipeRepository {

    suspend fun getSaveds(context: Context) : MutableList<SavedRecipe> =
        FoodDatabase.getInstance(context).savedRecipeDao().getSaveds()

    suspend fun insertSaved(recipe: SavedRecipe, context: Context) =
        FoodDatabase.getInstance(context).savedRecipeDao().saveRecipe(recipe)

    suspend fun removeSaved(recipe: SavedRecipe, context: Context) =
        FoodDatabase.getInstance(context).savedRecipeDao().removeSaved(recipe)

    suspend fun isRecipeSaved(uid: Int, context: Context): Boolean =
        FoodDatabase.getInstance(context).savedRecipeDao().isRecipeExists(uid)

    suspend fun deleteAll(context: Context) {
        FoodDatabase.getInstance(context).savedRecipeDao().deleteAll()
    }

}