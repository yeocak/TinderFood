package com.yeocak.tinderfood.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeocak.tinderfood.model.Preference

@Dao
interface PreferenceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(item: Preference)

    @Query("SELECT * FROM Preference")
    suspend fun takePreference(): Preference

    @Query("SELECT EXISTS (SELECT * FROM Preference)")
    suspend fun isPreferenceExists(): Boolean
}