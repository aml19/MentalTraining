package com.example.mentaltraining

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
    Main Activity shows and keeps track of all menu items and their sub menu items until a new activity is needed
 */
class MainActivity : AppCompatActivity(), View.OnClickListener, MainRecyclerViewAdapter.OnRecyclerItemClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fill recycler view with main categories array elements
        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = MainRecyclerViewAdapter(resources.getStringArray(R.array.main_categories), this)
        recyclerView.adapter = adapter

        //testing cards : WORKS!!!!
        val deck : Deck = Deck()
        deck.shuffle()
    }

    //Might not need this method
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    /*
        Calls SettingsActivity and passes name of selected category
     */
    override fun onItemClick(position: Int) {
        //call new activity and pass name of category
        val intent = Intent(this@MainActivity, SettingsActivity::class.java)
        intent.putExtra("Name", resources.getStringArray(R.array.main_categories)[position])
        startActivity(intent)
    }


}
