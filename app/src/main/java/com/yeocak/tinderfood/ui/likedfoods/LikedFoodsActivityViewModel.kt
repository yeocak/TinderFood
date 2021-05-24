package com.yeocak.tinderfood.ui.likedfoods

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeocak.tinderfood.database.FoodRepository
import com.yeocak.tinderfood.model.recipes.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikedFoodsActivityViewModel: ViewModel() {
    val likedFoodsList = mutableListOf<Recipe>()
    var listChanged = MutableLiveData<Boolean>()

    fun getLikedFoods(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            val taking = FoodRepository.getAllFoods(context)
            if (taking != null) {
                for(recipe in taking){
                    likedFoodsList.add(recipe)
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                listChanged.value = true
            }
        }
    }
}