package com.example.mentaltraining

import java.lang.Math.random
import java.util.*
import kotlin.collections.ArrayList

class Deck {

    private var mCards : Array<Card> = arrayOf<Card>()

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
    }
}

