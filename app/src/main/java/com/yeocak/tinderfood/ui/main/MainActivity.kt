package com.yeocak.tinderfood.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeocak.tinderfood.database.SavedRecipeRepository
import com.yeocak.tinderfood.databinding.ActivityMainBinding
import com.yeocak.tinderfood.ui.discover.DiscoverActivity
import com.yeocak.tinderfood.ui.likedfoods.LikedFoodsActivity
import com.yeocak.tinderfood.ui.preferences.PreferencesActivity
import com.yeocak.tinderfood.ui.settings.SettingsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFavourites()

        binding.discoverNewFoods.setOnClickListener {
            val intentAction = Intent(this, DiscoverActivity::class.java)
            startActivity(intentAction)
        }

        binding.likedFoods.setOnClickListener {
            val intentAction = Intent(this, LikedFoodsActivity::class.java)
            startActivity(intentAction)
        }

        binding.preferences.setOnClickListener {
            val intentAction = Intent(this, PreferencesActivity::class.java)
            // TODO("NOT DONE")
            startActivity(intentAction)
        }

        binding.settings.setOnClickListener {
            val intentAction = Intent(this, SettingsActivity::class.java)
            startActivity(intentAction)
        }
    }

    override fun onResume() {
        super.onResume()
        setFavourites()
    }

    private fun setFavourites(){
        CoroutineScope(Dispatchers.IO).launch {
            val favouritesList = SavedRecipeRepository.getSaveds(this@MainActivity)
            withContext(Dispatchers.Main){
                val adapter = FavouritesAdapter(favouritesList, this@MainActivity)
                binding.rvFavourites.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rvFavourites.adapter = adapter
            }
        }
    }
}