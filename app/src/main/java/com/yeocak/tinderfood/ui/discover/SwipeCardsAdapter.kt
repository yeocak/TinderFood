package com.yeocak.tinderfood.ui.discover

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeocak.simpleimageload.SimpleImageLoad.loadImage
import com.yeocak.tinderfood.databinding.SingleFoodCardBinding
import com.yeocak.tinderfood.R
import com.yeocak.tinderfood.model.DiscoverCard
import com.yeocak.tinderfood.model.VeganType

class SwipeCardsAdapter(
    private val cardList: MutableList<DiscoverCard>,
    private val context: Context
) : RecyclerView.Adapter<SwipeCardsAdapter.SwipeCardsViewHolder>() {

    class SwipeCardsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = SingleFoodCardBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeCardsViewHolder {
        return SwipeCardsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_food_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SwipeCardsViewHolder, position: Int) {
        val current = cardList[position]

        with(holder.binding){
            tvFoodName.text = current.name
            tvTime.text = current.time
            tvServings.text = current.serving
            tvIngredients.text = current.ingredients

            ivFoodPhoto.loadImage(current.photoLink, context) // Round corners 50


            when(current.vegan){
                (VeganType.VEGAN) ->{
                    tvVegan.text = "Vegan"
                }
                (VeganType.VEGETARIAN) ->{
                    tvVegan.text = "Vegetarian"
                }
                else ->{
                    tvVegan.text = "Not Vegan/Vegetarian"
                }
            }

            scrollView2.setOnTouchListener { v, event ->
                v.parent.requestDisallowInterceptTouchEvent(true)

                false
            }
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

}