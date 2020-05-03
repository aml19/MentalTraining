package com.example.mentaltraining

import android.widget.ImageView
import java.lang.Math.random
import java.util.*
import kotlin.collections.ArrayList

class Deck {

    private var mCards : Array<Card> = arrayOf<Card>()

    //initializes deck with cards
    init{
        make()
    }

    //fills the deck with cards of standard 52 playing card deck
    private fun make(){
        val result : ArrayList<Card> = arrayListOf()    //holds all the cards
        //goes through each iteration of cards
        for(i in 0..3){
            for(j in 2..14){
                result.add(Card(Card.Suit.from(i), j))
            }
        }
        this.mCards = result.toTypedArray()
    }

    fun shuffle() {
        val rng = Random()
        for(i in this.mCards.indices){
            val randomIndex = rng.nextInt(i+1)
            val temp = this.mCards[i]
            this.mCards[i] = this.mCards[randomIndex]
            this.mCards[randomIndex] = temp
        }
    }

    /*
        private class Card() has suit/value like regular playing card deck
     */
    private class Card (suit : Suit, value : Int){
        enum class Suit (val value : Int){
            DIAMOND(0),
            CLUB(1),
            HEART(2),
            SPADE(3);
            //returns enumerated value from an integer
            companion object{
                fun from(findValue : Int) : Suit = values().first {it.value == findValue}

            }
        }
        var mSuit : Suit = Suit.DIAMOND
        var mValue :Int = 0
        var mName : String = ""
        //constructor that sets private member fields
        init{
            mSuit = suit
            mValue = value
            mName = convertValToString(value) + " of " + suit.toString()
        }

        //converts card value to string (for face cards mostly)
        private fun convertValToString(value : Int) : String{
            return when(value){
                11 -> "Jack"
                12 -> "Queen"
                13 -> "King"
                14 -> "Ace"
                else ->{
                    value.toString()
                }
            }
        }

        //get resource ID of the image file corresponding to the card
        fun getImageFileID() : Int {
            return when (this.mSuit) {
                Suit.DIAMOND -> {
                    when(this.mValue){
                        2 -> R.drawable.two_diamonds
                        3-> R.drawable.three_diamonds
                        4-> R.drawable.four_diamonds
                        5-> R.drawable.five_diamonds
                        6-> R.drawable.six_diamonds
                        7-> R.drawable.seven_diamonds
                        8-> R.drawable.eight_diamonds
                        9-> R.drawable.nine_diamonds
                        10-> R.drawable.ten_diamonds
                        11-> R.drawable.jack_diamonds
                        12-> R.drawable.queen_diamonds
                        13-> R.drawable.king_diamonds
                        14-> R.drawable.ace_diamonds
                        else-> 2
                    }
                }
                Suit.CLUB -> {
                    when(this.mValue){
                        2 -> R.drawable.two_clubs
                        3-> R.drawable.three_clubs
                        4-> R.drawable.four_clubs
                        5-> R.drawable.five_clubs
                        6-> R.drawable.six_clubs
                        7-> R.drawable.seven_clubs
                        8-> R.drawable.eight_clubs
                        9-> R.drawable.nine_clubs
                        10-> R.drawable.ten_clubs
                        11-> R.drawable.jack_clubs
                        12-> R.drawable.queen_clubs
                        13-> R.drawable.king_clubs
                        14-> R.drawable.ace_clubs
                        else-> 2
                    }
                }
                Suit.HEART -> {
                    when(this.mValue){
                        2 -> R.drawable.two_hearts
                        3-> R.drawable.three_hearts
                        4-> R.drawable.four_hearts
                        5-> R.drawable.five_hearts
                        6-> R.drawable.six_hearts
                        7-> R.drawable.seven_hearts
                        8-> R.drawable.eight_hearts
                        9-> R.drawable.nine_hearts
                        10-> R.drawable.ten_hearts
                        11-> R.drawable.jack_hearts
                        12-> R.drawable.queen_hearts
                        13-> R.drawable.king_hearts
                        14-> R.drawable.ace_hearts
                        else-> 2
                    }
                }
                else -> {
                    when(this.mValue){
                        2 -> R.drawable.two_spades
                        3-> R.drawable.three_spades
                        4-> R.drawable.four_spades
                        5-> R.drawable.five_spades
                        6-> R.drawable.six_spades
                        7-> R.drawable.seven_spades
                        8-> R.drawable.eight_spades
                        9-> R.drawable.nine_spades
                        10-> R.drawable.ten_spades
                        11-> R.drawable.jack_spades
                        12-> R.drawable.queen_spades
                        13-> R.drawable.king_spades
                        14-> R.drawable.ace_spades
                        else-> 2
                    }
                }
            }
        }
    }
}

