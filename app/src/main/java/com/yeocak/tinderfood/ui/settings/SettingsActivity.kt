package com.yeocak.tinderfood.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yeocak.tinderfood.databinding.ActivitySettingsBinding
import com.yeocak.tinderfood.ui.preferences.PreferencesActivityViewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var viewModel: SettingsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SettingsActivityViewModel::class.java)

        binding.btnGoGithub.setOnClickListener {
            val link = "https://github.com"
            val intentAction = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intentAction)
        }

        binding.btnRemoveLikeds.setOnClickListener {
            viewModel.removeAllLikeds(this)
        }
    }
}