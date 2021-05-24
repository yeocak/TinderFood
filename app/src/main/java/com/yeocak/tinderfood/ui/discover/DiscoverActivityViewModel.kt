package com.yeocak.tinderfood.ui.discover

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yeocak.tinderfood.database.FoodRepository
import com.yeocak.tinderfood.model.recipes.RetrofitRandomFood
import com.yeocak.tinderfood.retrofit.FoodService
import com.yeocak.tinderfood.utils.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DiscoverActivityViewModel: ViewModel() {
    var foodResponse : LiveData<Response<RetrofitRandomFood>>? = null
    private var cardCounter = 1

    fun increaseCardCounter() {cardCounter += 1}
    fun getCardCounter() = cardCounter

    fun swipedRight(context: Context){
        val currentCard = foodResponse?.value?.body()?.recipes?.get(cardCounter-1)
        CoroutineScope(Dispatchers.IO).launch {
            if (currentCard != null) {
                FoodRepository.insertFood(currentCard,context)
            }
        }
    }

    fun loadRandom15Food(){
        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(FoodService::class.java)

        foodResponse = liveData {
            val response = retService.getFood()
                emit(response)
        }
    }
}