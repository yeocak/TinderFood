package com.yeocak.tinderfood.ui.discover

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yeocak.tinderfood.databinding.ActivityDiscoverBinding
import com.yeocak.tinderfood.model.DiscoverCard
import com.yeocak.tinderfood.model.VeganType
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod

class DiscoverActivity : AppCompatActivity(), CardStackListener {

    private lateinit var binding: ActivityDiscoverBinding
    private lateinit var viewModel: DiscoverActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DiscoverActivityViewModel::class.java)

        setUpScreen()

        setUpSwipeCards()
    }

    private fun setUpScreen(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setUpSwipeCards(){

        val cardList = mutableListOf<DiscoverCard>()
        val swipeAdapter = SwipeCardsAdapter(cardList, this)

        viewModel.loadRandom15Food()

        viewModel.foodResponse?.observe(this, Observer {
            it.body()?.recipes?.forEach { recipe ->
                var vegan = VeganType.NONE
                if(recipe.vegan){
                    vegan = VeganType.VEGAN
                } else if(recipe.vegetarian){
                    vegan = VeganType.VEGETARIAN
                }

                var ingredients = ""

                for (a in recipe.extendedIngredients){
                    if(!ingredients.contains(a.nameClean)){
                        ingredients += "${a.nameClean}\n"
                    }
                }

                recipe.let {
                    val newCard = DiscoverCard(
                        recipe.image,
                        recipe.title,
                        recipe.readyInMinutes.toString() + " minutes",
                        recipe.servings.toString() + " servings",
                        vegan,
                        ingredients
                    )

                    cardList.add(newCard)
                }
            }
            swipeAdapter.notifyDataSetChanged()
        })

        binding.swipeCards.layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.Manual)
            setOverlayInterpolator(LinearInterpolator())
        }
        binding.swipeCards.adapter = swipeAdapter
    }

    override fun onCardSwiped(direction: Direction?) {
        if(direction == Direction.Right){
            viewModel.swipedRight(this)
        } else if(direction == Direction.Left){ }
        viewModel.increaseCardCounter()
        binding.tvCounter.text = "${viewModel.getCardCounter()}/15"
    }

    //Nothing for this functions
    override fun onCardDragging(direction: Direction?, ratio: Float) {}
    override fun onCardRewound() {}
    override fun onCardCanceled() {}
    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardDisappeared(view: View?, position: Int) {}

}