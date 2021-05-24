package com.yeocak.tinderfood.ui.preferences

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.yeocak.tinderfood.database.PreferenceRepository
import com.yeocak.tinderfood.model.Preference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PreferencesActivityViewModel: ViewModel() {
    var currentPreference = Preference()
    var savedPreference = Preference()

    suspend fun getPreference(context: Context){
        if(PreferenceRepository.isPreferenceExists(context)){
            savedPreference = PreferenceRepository.takePreference(context)
            currentPreference.equalizePreferences(savedPreference)
        }
    }

    fun savePreference(context: Context){
        runBlocking {
            PreferenceRepository.insertPreference(currentPreference, context)
            savedPreference.equalizePreferences(currentPreference)
        }
    }

    fun checkChanges(): Boolean{
        return currentPreference == savedPreference
    }

    fun Preference.equalizePreferences(changer: Preference){
        this.veganType = changer.veganType
        this.cheap = changer.cheap
        this.dairy = changer.dairy
        this.gluten = changer.gluten
        this.healthy = changer.healthy
        this.popular = changer.popular
    }

}