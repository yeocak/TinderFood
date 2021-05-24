package com.yeocak.tinderfood.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.bumptech.glide.Glide
import com.yeocak.tinderfood.database.PhotoRepository
import com.yeocak.tinderfood.model.Photo
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.lang.Exception

object ImageProgress {

    suspend fun takeImage(link: String, context: Context): Photo? {

        var newPhoto = PhotoRepository.getPhoto(link, context)

        if(newPhoto == null){
            try {
                val downloadedBitmap = downloadImageAsBitmap(link,context)
                val scaledBitmap = scaleBitmap(downloadedBitmap!!)
                val downloadedString = bitmapToString(scaledBitmap)

                newPhoto = Photo(
                    link,
                    downloadedString!!
                )

                PhotoRepository.insertPhoto(newPhoto,context)
            } catch (e:Exception){
                Log.d("CustomError", "Something went wrong in ImageProgress! (Fun ImageProgress)")
            }
        }
        return newPhoto
    }

    fun stringToBitmap(image: String?): Bitmap? {
        val decodedByteArray: ByteArray = Base64.decode(image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
    }

    private fun downloadImageAsBitmap(imageURL: String, context: Context): Bitmap? {
        return Glide.with(context)
            .asBitmap()
            .load(imageURL)
            .submit()
            .get()
    }

    private fun bitmapToString(image: Bitmap?): String? {
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 0, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun scaleBitmap(image: Bitmap): Bitmap {
        val ratio: Double = image.height.toDouble() / image.width.toDouble()
        val customLength = 600.0

        var newHeight = customLength
        var newWidth = customLength

        if (ratio > 1) {
            newWidth *= (1 / ratio)
        } else {
            newHeight *= (ratio)
        }

        return Bitmap.createScaledBitmap(image, newWidth.toInt(), newHeight.toInt(), false)
    }

}