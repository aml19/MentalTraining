package com.example.mentaltraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/*
    take an array of images and fills a recycler view with them
 */
class CardImageRecyclerViewAdapter(private val deck : Deck) : RecyclerView.Adapter<CardImageRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardImageRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.playing_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return deck.getSize()
    }

    override fun onBindViewHolder(holder: CardImageRecyclerViewAdapter.ViewHolder, position: Int) {
        //pass in resource ID of the card so it will bind it to an imageView
        holder.bindItems(deck.getAtIndex(position).getImageFileID(), position)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(id : Int, position : Int){    //sets the image view to a card.  ID corresponds to a jpg file in resources
            val view = itemView.findViewById(R.id.playing_card_view) as ImageView
            view.setImageResource(id)

        }
    }

}