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
class MainActivity : AppCompatActivity(), MainRecyclerViewAdapter.OnRecyclerItemClick, View.OnClickListener {

    private var mCurrentDisplayItemArray : Array<DisplayListItem> = arrayOf()   //keeps track of list of items to be displayed in recycler view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //setting up toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        mCurrentDisplayItemArray = createLists()    //main categories on menu

        // Setup the recyclerView and set the adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = MainRecyclerViewAdapter(getAllNames(mCurrentDisplayItemArray), this)
        recyclerView.adapter = adapter

        //setting up back button
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener(this)

        //testing cards : WORKS!!!!
        val deck : Deck = Deck()
        deck.shuffle()
    }

    /*
        -this is for non Display List Item ojects such as:
            -back button
     */
    override fun onClick(v: View?) {

        //all siblings have the same parent so any will do.  Choose first because each View will always have at least
        val parent = mCurrentDisplayItemArray[0].getParent()
        if(parent.name != "null"){      //check if there is a parent, highest tier items don't have parents
            val siblings = parent.getAllSiblings()    //gets siblings of parent

            //change current display item list to the siblings of parent
            mCurrentDisplayItemArray = siblings

            //Make new recycler view
            var adapter = MainRecyclerViewAdapter(getAllNames(mCurrentDisplayItemArray), this)
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter

            //refreshes the adapter
            adapter.notifyDataSetChanged()
        }
    }

    /*
        -onClickListener is set in the MainRecyclerViewAdapter class on each item in the list
        -Changes the current list of items to the subList of items
     */
    override fun onItemClick(position: Int) {
        Log.d(ContentValues.TAG, "Main Activity $position.")

        val itemSelected = mCurrentDisplayItemArray[position]   //get item selected

        //check if item needs to start another activity and parse out which activity to go to
        if(itemSelected.mHasActivity) {

            //Find activity class to go to based on the Main Category the item belongs to
            val mainCategories = itemSelected.getMainCategoryName()
            var intent = Intent()
            when(mainCategories){
                "Arithmetic" -> {
                    intent = Intent(this@MainActivity, ArithmeticActivity::class.java)
                    intent.putExtra("itemSelected", operationType(itemSelected))
                    // Now we can start the Activity, providing the activity options as a bundle
                    //val test : Bundle = Bundle()
                    //ActivityCompat.startActivity(this@MainActivity, intent, test)
                }
                "Speed Numbers" -> {
                    intent = Intent(this@MainActivity, SpeedNumbersActivity::class.java)
                }

            }
            startActivity(intent)
        }
        else{
            //change mCurrentDisplayItemArray to the newly selected item list
            mCurrentDisplayItemArray = itemSelected.subList

            //Make new recycler view
            var adapter = MainRecyclerViewAdapter(getAllNames(mCurrentDisplayItemArray), this)
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter

            //refreshes the adapter
            adapter.notifyDataSetChanged()
        }
    }

    /*
        Input: array of DisplayListItem
        Output: Array<String> of all the names of the DisplayListItem
     */
    private fun getAllNames(inputArray: Array<DisplayListItem>) : Array<String>{
        val result : ArrayList<String> = arrayListOf()
        for(i in inputArray){
            result.add(i.name)
        }
        return result.toTypedArray()
    }

    /*
        All the init data for menu items.
        TODO fill out the rest of events and their subgroups
     */
    fun createLists() : Array<DisplayListItem>{
        var result : ArrayList<DisplayListItem> = arrayListOf()

        //reuseables
        var start = DisplayListItem("Start", true)    //causing error
        var settings = DisplayListItem("Settings", true)
        //var siblings : Array<DisplayListItem>
        //val defaultOptions = arrayOf(start, settings)   //easier to use for now, will probably customize each one later

        //Arithmetic
        val addition = DisplayListItem("Addition", arrayOf(DisplayListItem("Start", true), DisplayListItem("Settings")))
        val subtraction = DisplayListItem("Subtraction", arrayOf(DisplayListItem("Start", true), DisplayListItem("Settings")))
        val multiplication = DisplayListItem("Multiplication", arrayOf(DisplayListItem("Start", true), DisplayListItem("Settings")))
        val division = DisplayListItem("Division", arrayOf(DisplayListItem("Start", true), DisplayListItem("Settings")))
        val siblings1 = arrayOf(addition, subtraction, multiplication, division)
        addition.setSiblings(siblings1)
        subtraction.setSiblings(siblings1)
        multiplication.setSiblings(siblings1)
        division.setSiblings(siblings1)

        val arithmetic = DisplayListItem("Arithmetic", arrayOf(addition, subtraction, multiplication, division))

        //Names and Faces
        val a = DisplayListItem("a", arrayOf(DisplayListItem("Start"), DisplayListItem("Settings")))
        val b = DisplayListItem("b", arrayOf(DisplayListItem("Start"), DisplayListItem("Settings")))
        val namesAndFaces = DisplayListItem("Names and Faces", arrayOf(a,b))

        //Speed Numbers
        val speedNumbers = DisplayListItem("Speed Numbers", DisplayListItem("Start", true))

        //Speed Cards
        val speedCards = DisplayListItem("Speed Cards", arrayOf(a,b))

        //Poetry
        val poetry = DisplayListItem("Poetry", arrayOf(a,b))

        //setting the siblings, has to be after everything else is inititalized
        val siblings2 = arrayOf(arithmetic, namesAndFaces, speedCards, speedNumbers, poetry)
        arithmetic.setSiblings(siblings2)
        namesAndFaces.setSiblings(siblings2)
        speedCards.setSiblings(siblings2)
        speedNumbers.setSiblings(siblings2)
        poetry.setSiblings(siblings2)
        result.add(poetry)
        result.add(speedCards)
        result.add(speedNumbers)
        result.add(arithmetic)
        result.add(namesAndFaces)

        result.sort()   //keeps list in alphabetical order

        return result.toTypedArray()
    }

    /*
        Gets the operation type from itemSelected to pass into ArithmeticActivity
     */
    fun operationType(item : DisplayListItem) : String {
        val parent = item.getParent().name
        //TODO make an enum to encapsulate these values so I'm not comparing hardcoded string values
        when (parent){
            //"null" -> return "null"
            "Addition" -> return "Addition"
            "Subtraction" -> return "Subtraction"
            "Multiplication" -> return "Multiplication"
            "Division" -> return "Division"
            else ->{
                operationType(item.getParent())
            }
        }
        return "null"
    }


}
