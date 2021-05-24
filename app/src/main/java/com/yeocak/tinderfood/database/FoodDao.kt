package com.yeocak.tinderfood.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeocak.tinderfood.model.recipes.Recipe

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodStr: Recipe)

    @Query("SELECT * FROM recipe WHERE (:id) == uid")
    suspend fun getFood(id: Int): Recipe?

    @Query("SELECT * FROM recipe")
    suspend fun getAllFoods(): List<Recipe>?

    @Query("DELETE FROM Recipe")
    suspend fun deleteAll()

}