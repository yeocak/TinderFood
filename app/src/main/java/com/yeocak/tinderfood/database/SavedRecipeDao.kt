package com.yeocak.tinderfood.database

import androidx.room.*
import com.yeocak.tinderfood.model.SavedRecipe

@Dao
interface SavedRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: SavedRecipe)

    @Delete
    suspend fun removeSaved(recipe: SavedRecipe)

    @Query("SELECT * FROM SavedRecipe")
    suspend fun getSaveds() : MutableList<SavedRecipe>

    @Query("SELECT EXISTS (SELECT * FROM SavedRecipe WHERE :uid == uid)")
    suspend fun isRecipeExists(uid: Int): Boolean

    @Query("DELETE FROM SavedRecipe")
    suspend fun deleteAll()

}