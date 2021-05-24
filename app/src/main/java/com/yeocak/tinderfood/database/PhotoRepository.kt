package com.yeocak.tinderfood.database

import android.content.Context
import com.yeocak.tinderfood.model.Photo

object PhotoRepository {

    suspend fun getPhoto(link: String, context: Context): Photo? =
        FoodDatabase.getInstance(context).photoDao().getPhoto(link)

    suspend fun insertPhoto(photo: Photo, context: Context) =
        FoodDatabase.getInstance(context).photoDao().insertPhoto(photo)

}