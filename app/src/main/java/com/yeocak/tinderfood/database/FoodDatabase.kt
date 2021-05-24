package com.yeocak.tinderfood.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yeocak.tinderfood.model.Photo
import com.yeocak.tinderfood.model.Preference
import com.yeocak.tinderfood.model.SavedRecipe
import com.yeocak.tinderfood.model.recipes.Recipe
import com.yeocak.tinderfood.utils.FoodRoomTypeConverter

@Database(
    entities = [Recipe::class, Photo::class, Preference::class, SavedRecipe::class],
    version = 2
)
@TypeConverters(FoodRoomTypeConverter::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun photoDao(): PhotoDao
    abstract fun preferenceDao(): PreferenceDao
    abstract fun savedRecipeDao(): SavedRecipeDao

    companion object {

        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            synchronized(this) {
                INSTANCE.let {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            FoodDatabase::class.java,
                            "TinderFood"
                        ).fallbackToDestructiveMigration()
                            .build()
                }
                return INSTANCE!!
            }
        }

    }
}