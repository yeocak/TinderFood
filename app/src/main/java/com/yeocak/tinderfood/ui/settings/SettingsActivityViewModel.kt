package com.yeocak.tinderfood.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.yeocak.tinderfood.database.FoodRepository
import com.yeocak.tinderfood.database.SavedRecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsActivityViewModel : ViewModel() {

    fun removeAllLikeds(context: Context){
        CoroutineScope(Dispatchers.IO).launch{
            FoodRepository.deleteAll(context)
            SavedRecipeRepository.deleteAll(context)
        }
    }

}