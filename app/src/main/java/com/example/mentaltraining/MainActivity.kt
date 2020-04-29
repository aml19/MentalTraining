package com.example.mentaltraining

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*TODO
    -implement pixel back button
    -make separate list of sub categories Addition -> num digits, decimals, etc
        -or do settings
    -make settings file for each subcategory
    -make separate activity for each
 */

/*
    Main Activity shows and keeps track of all menu items and their sub menu items until a new activity is needed

 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting up toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        // Setup the recyclerView and set the adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //setting up back button
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener(this)

        //testing cards : WORKS!!!!
        val deck : Deck = Deck()
        deck.shuffle()
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
