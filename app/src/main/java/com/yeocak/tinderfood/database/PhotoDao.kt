package com.yeocak.tinderfood.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeocak.tinderfood.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos WHERE (:link) == photoLink")
    suspend fun getPhoto(link: String): Photo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: Photo)

}