package com.example.mentaltraining

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_memory_cards.*

class CardsActivity : AppCompatActivity(), View.OnClickListener, MainRecyclerViewAdapter.OnRecyclerItemClick {

    var mAnswerDeck = Deck()
    var mDeck = Deck()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_cards)

        //create unshuffled deck of cards
        mDeck.make()
        mDeck.shuffle()

        //set adapter
        val recyclerView = findViewById<RecyclerView>(R.id.card_view_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = CardImageRecyclerViewAdapter(mDeck)
        recyclerView.adapter = adapter

        //set onClickListener for button to change to answer page
        val button = findViewById<Button>(R.id.memory_card_start_button)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.memory_card_start_button ->{
                setContentView(R.layout.activity_card_answer_layout)
                //set onclick listeners and adapters
                val recyclerView = findViewById<RecyclerView>(R.id.answer_card_view_list_of_cards)
                recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                val adapter = CardImageRecyclerViewAdapter(Deck())
                recyclerView.adapter = adapter
                val addCardButton = findViewById<Button>(R.id.card_answer_addCard_button)
                addCardButton.setOnClickListener(this)
            }
            //add designated card to recyclerView
            R.id.card_answer_addCard_button->{
                val rankSpinner = findViewById<Spinner>(R.id.card_rank)
                val suitSpinner = findViewById<Spinner>(R.id.card_suit)
                val card = Deck.Card(suitSpinner.selectedItem.toString(), rankSpinner.selectedItem.toString())
                mAnswerDeck.addCard(card)
                val recyclerView = findViewById<RecyclerView>(R.id.answer_card_view_list_of_cards)
                val adapter = CardImageRecyclerViewAdapter(mAnswerDeck)
                recyclerView.adapter = adapter
                recyclerView.scrollToPosition(mAnswerDeck.getSize()-1)
                adapter.notifyDataSetChanged()
            }
            R.id.card_answer_submit_button->{

            }
        }
    }
    override fun onItemClick(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_LONG).show()
    }
}
