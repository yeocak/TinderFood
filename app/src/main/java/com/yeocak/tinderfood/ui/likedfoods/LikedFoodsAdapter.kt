package com.yeocak.tinderfood.ui.likedfoods

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yeocak.tinderfood.databinding.SingleFoodMenuBinding
import com.yeocak.tinderfood.R
import com.yeocak.tinderfood.database.SavedRecipeRepository
import com.yeocak.tinderfood.model.Photo
import com.yeocak.tinderfood.model.SavedRecipe
import com.yeocak.tinderfood.model.recipes.Recipe
import com.yeocak.tinderfood.utils.ImageProgress
import kotlinx.coroutines.*

class LikedFoodsAdapter(
    private val recipeList: MutableList<Recipe>,
    private val context: Context
) : RecyclerView.Adapter<LikedFoodsAdapter.LikedFoodsViewHolder>() {

    class LikedFoodsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SingleFoodMenuBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedFoodsViewHolder {
        return LikedFoodsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_food_menu,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LikedFoodsViewHolder, position: Int) {
        val current = recipeList[position]

        setMenu(holder, current)

        holder.binding.nsvInstructions.setOnTouchListener { v, _ ->
            v.parent.requestDisallowInterceptTouchEvent(true)

            false
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    private fun setMenu(holder: LikedFoodsViewHolder, current: Recipe){
        with(holder.binding){

            CoroutineScope(Dispatchers.Main).launch {
                var currentPhoto: Photo?

                withContext(Dispatchers.IO){
                    currentPhoto = ImageProgress.takeImage(current.image, context)
                }

                Glide.with(context)
                    .load(currentPhoto?.photoLink)
                    .transform(RoundedCorners(50))
                    .into(ivMenuFoodPhoto)
            }

            star(holder, current.uid, current.title)

            tvMenuTitle.text = current.title
            tvMenuTime.text = "${current.readyInMinutes} minutes"
            tvMenuServings.text = "${current.servings} servings"
            tvMenuInstructions.text = current.instructions

            if(current.vegan){
                tvMenuVegan.text = "Vegan"
            } else if(current.vegetarian){
                tvMenuVegan.text = "Vegetarian"
            } else{
                tvMenuVegan.text = "Not vegan/vegetarian"
            }

            if(current.cheap){
                tvMenuCheap.text = "Cheap"
            } else{
                tvMenuCheap.text = "Not cheap"
            }

            if(current.dairyFree){
                tvMenuDairy.text = "Dairy free"
            } else{
                tvMenuDairy.text = "Not dairy free"
            }

            if(current.veryPopular){
                tvMenuPopular.text = "Popular"
            } else{
                tvMenuPopular.text = "Not popular"
            }

            if(current.glutenFree){
                tvMenuGluten.text = "Gluten free"
            } else{
                tvMenuGluten.text = "Not gluten free"
            }

            if(current.veryHealthy){
                tvMenuHealthy.text = "Very healthy"
            } else{
                tvMenuHealthy.text = "Not very healthy"
            }
        }
    }

    private fun star(holder: LikedFoodsViewHolder, uid: Int, name: String){
        var star = false

        CoroutineScope(Dispatchers.IO).launch {
            star = SavedRecipeRepository.isRecipeSaved(uid, context)
            withContext(Dispatchers.Main){
                checkStar(star, holder)
            }
        }

        holder.binding.layoutSaved.setOnClickListener {
            val recipe = SavedRecipe(uid, name)
            CoroutineScope(Dispatchers.IO).launch {
                if(star){
                    SavedRecipeRepository.removeSaved(recipe, context)
                } else{
                    SavedRecipeRepository.insertSaved(recipe, context)
                }

                withContext(Dispatchers.Main){
                    star = !star
                    checkStar(star, holder)
                }
            }
        }
    }

    private fun checkStar(star: Boolean, holder: LikedFoodsViewHolder){
        if(star){
            holder.binding.ivMenuStarSaved.visibility = VISIBLE
        } else{
            holder.binding.ivMenuStarSaved.visibility = GONE
        }
    }
}