package com.yeocak.tinderfood.ui.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.yeocak.tinderfood.databinding.ActivityPreferencesBinding
import com.yeocak.tinderfood.model.Preference
import com.yeocak.tinderfood.model.VeganType
import kotlinx.coroutines.runBlocking

class PreferencesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferencesBinding
    private lateinit var viewModel: PreferencesActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(PreferencesActivityViewModel::class.java)


        runBlocking { viewModel.getPreference(this@PreferencesActivity) }
        setSeekbars(viewModel.savedPreference)

        listenChanges()

        binding.btnPreferenceSave.setOnClickListener {
            viewModel.savePreference(this)
            changeButtons()
        }

        binding.btnPreferenceCancel.setOnClickListener {
            with(viewModel){
                currentPreference.equalizePreferences(savedPreference)
            }
            setSeekbars(viewModel.savedPreference)
        }

        binding.btnPreferenceBack.setOnClickListener {
            finish()
        }

        binding.btnPreferenceReset.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)

            alertDialogBuilder
                .setTitle("Reset preferences")
                .setMessage("Do you want to reset your preferences")
                .setPositiveButton("Yes") { _, _ ->
                    with(viewModel){
                        currentPreference.equalizePreferences(Preference())
                        savedPreference.equalizePreferences(Preference())
                        savePreference(this@PreferencesActivity)
                        setSeekbars(Preference())
                        changeButtons()
                    }
                }
                .setNegativeButton("No") { _, _ -> }

            alertDialogBuilder.show()
        }
    }

    private fun setSeekbars(preference: Preference){
        when(preference.veganType){
            VeganType.VEGAN -> binding.sbVegan.progress = 0
            VeganType.VEGETARIAN -> binding.sbVegan.progress = 2
            VeganType.NONE -> binding.sbVegan.progress = 1
        }

        if(preference.cheap != null) {
            if(preference.cheap!!){
                binding.sbCheap.progress = 2
            } else {
                binding.sbCheap.progress = 0
            }
        } else {
            binding.sbCheap.progress = 1
        }

        if(preference.dairy != null) {
            if(preference.dairy!!){
                binding.sbDairy.progress = 2
            } else {
                binding.sbDairy.progress = 0
            }
        } else {
            binding.sbDairy.progress = 1
        }

        if(preference.gluten != null) {
            if(preference.gluten!!){
                binding.sbGluten.progress = 2
            } else {
                binding.sbGluten.progress = 0
            }
        } else {
            binding.sbGluten.progress = 1
        }

        if(preference.healthy != null) {
            if(preference.healthy!!){
                binding.sbHealthy.progress = 2
            } else {
                binding.sbHealthy.progress = 0
            }
        } else {
            binding.sbHealthy.progress = 1
        }

        if(preference.popular != null) {
            if(preference.popular!!){
                binding.sbPopular.progress = 2
            } else {
                binding.sbPopular.progress = 0
            }
        } else {
            binding.sbPopular.progress = 1
        }
    }

    private fun changeButtons(){
        if(viewModel.checkChanges()){
            binding.layoutPreferenceChange.visibility = GONE
        } else{
            binding.layoutPreferenceChange.visibility = VISIBLE
        }
    }

    private fun listenChanges(){
        binding.sbPopular.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i == 0){
                    viewModel.currentPreference.popular = false
                } else if(i == 2){
                    viewModel.currentPreference.popular = true
                } else {
                    viewModel.currentPreference.popular = null
                }

                changeButtons()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.sbHealthy.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i == 0){
                    viewModel.currentPreference.healthy = false
                } else if(i == 2){
                    viewModel.currentPreference.healthy = true
                } else {
                    viewModel.currentPreference.healthy = null
                }

                changeButtons()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.sbGluten.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i == 0){
                    viewModel.currentPreference.gluten = false
                } else if(i == 2){
                    viewModel.currentPreference.gluten = true
                } else {
                    viewModel.currentPreference.gluten = null
                }

                changeButtons()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.sbDairy.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i == 0){
                    viewModel.currentPreference.dairy = false
                } else if(i == 2){
                    viewModel.currentPreference.dairy = true
                } else {
                    viewModel.currentPreference.dairy = null
                }

                changeButtons()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.sbCheap.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i == 0){
                    viewModel.currentPreference.cheap = false
                } else if(i == 2){
                    viewModel.currentPreference.cheap = true
                } else {
                    viewModel.currentPreference.cheap = null
                }

                changeButtons()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.sbVegan.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i == 0){
                    viewModel.currentPreference.veganType = VeganType.VEGAN
                } else if(i == 2){
                    viewModel.currentPreference.veganType = VeganType.VEGETARIAN
                } else {
                    viewModel.currentPreference.veganType = VeganType.NONE
                }

                changeButtons()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}