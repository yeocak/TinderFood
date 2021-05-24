package com.yeocak.tinderfood.database

import android.content.Context
import com.yeocak.tinderfood.model.Preference

object PreferenceRepository {

    suspend fun insertPreference(preference: Preference, context: Context) =
        com.yeocak.tinderfood.database.FoodDatabase.getInstance(context).preferenceDao().insertPreference(preference)

    suspend fun takePreference(context: Context) =
        com.yeocak.tinderfood.database.FoodDatabase.getInstance(context).preferenceDao().takePreference()

    suspend fun isPreferenceExists(context: Context) =
        com.yeocak.tinderfood.database.FoodDatabase.getInstance(context).preferenceDao().isPreferenceExists()
}