package com.yeocak.tinderfood.ui.likedfoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeocak.tinderfood.databinding.ActivityLikedFoodsBinding

class LikedFoodsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLikedFoodsBinding
    private lateinit var viewModel : LikedFoodsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikedFoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LikedFoodsActivityViewModel::class.java)

        val scrollUid = intent.getIntExtra("uid",0)
        var notScrolled = true

        val adapter = LikedFoodsAdapter(viewModel.likedFoodsList, this)
        setRV(adapter)
        viewModel.getLikedFoods(this)
        viewModel.listChanged.observe(this, Observer {
            if(it){
                updateRV(adapter)
                viewModel.listChanged.value = false

                if(notScrolled && scrollUid != 0){
                    var position = 0
                    for(a in 1 until viewModel.likedFoodsList.size){
                        if(viewModel.likedFoodsList[a].uid == scrollUid){
                            position = a
                        }
                    }
                    binding.rvLikedFoods.scrollToPosition(position)
                    notScrolled = false
                }
            }
        })

        binding.btnLikedBack.setOnClickListener {
            finish()
        }

    }

    private fun setRV(adapter: LikedFoodsAdapter){
        binding.rvLikedFoods.layoutManager = LinearLayoutManager(this)
        binding.rvLikedFoods.isNestedScrollingEnabled = true
        binding.rvLikedFoods.adapter = adapter
    }

    private fun updateRV(adapter: LikedFoodsAdapter){
        adapter.notifyDataSetChanged()
    }
}