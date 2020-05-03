package com.example.mentaltraining

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*
    take an array of images and fills a recycler view with them
 */
class CardImageRecyclerViewAdapter(private val deck : Deck) : RecyclerView.Adapter<CardImageRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardImageRecyclerViewAdapter.ViewHolder {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CardImageRecyclerViewAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ViewHolder(imageView : View) : RecyclerView.ViewHolder(imageView){

        //sets the image view to a card
        fun bindItems()
    }

}