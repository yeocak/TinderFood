package com.yeocak.tinderfood.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.yeocak.tinderfood.R
import com.yeocak.tinderfood.database.SavedRecipeRepository
import com.yeocak.tinderfood.databinding.SingleFavouriteBinding
import com.yeocak.tinderfood.model.SavedRecipe
import com.yeocak.tinderfood.ui.likedfoods.LikedFoodsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesAdapter(
    private var favouritesList: MutableList<SavedRecipe>,
    val context: Context
) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    class FavouritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SingleFavouriteBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_favourite,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val current = favouritesList[position]

        setMenu(holder.binding, current)
    }

    override fun getItemCount(): Int {
        return favouritesList.size
    }

    private fun setMenu(binding: SingleFavouriteBinding, current: SavedRecipe) {
        binding.tvFavouriteName.text = current.name

        binding.ivFavouriteStarSaved.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(context)

            alertDialogBuilder
                .setTitle("Remove from favourites")
                .setMessage("Do you want to remove this food from your favourites list?")
                .setPositiveButton("Yes") { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        SavedRecipeRepository.removeSaved(current, context)
                        favouritesList.remove(current)
                        withContext(Dispatchers.Main) {
                            notifyDataSetChanged()
                        }
                    }
                }
                .setNegativeButton("No") { _, _ -> }

            alertDialogBuilder.show()
        }

        binding.tvFavouriteName.setOnClickListener {
            val intentAction = Intent(context, LikedFoodsActivity::class.java)
            intentAction.putExtra("uid",current.uid)
            context.startActivity(intentAction)
        }
    }
}