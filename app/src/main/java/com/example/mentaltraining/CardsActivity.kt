package com.example.mentaltraining

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_memory_cards.*

class CardsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_cards)

        //create unshuffled deck of cards
        val deck = Deck()

        //set adapter
        val recyclerView = findViewById<RecyclerView>(R.id.card_view_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = CardImageRecyclerViewAdapter(deck)
        recyclerView.adapter = adapter

        //set onClickListener for button to change to answer page
        val button = findViewById<Button>(R.id.memory_card_start_button)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
