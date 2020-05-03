package com.example.mentaltraining

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import androidx.preference.PreferenceManager


class SpeedNumbersActivity : AppCompatActivity(), View.OnClickListener {

    var toggleButton = true    //keeps track of reading or answering textView
    //variable to hold 2 dimensional array of numbers to be displayed
    var mNumbers2DArray = arrayOf<IntArray>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speed_numbers_main_layout)

        //load preference data
        val preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        //if null return, sets default value to 5
        val rows = preferences.getString("speed_num_row", "NULL")?.toInt() ?: 5
        val cols = preferences.getString("speed_num_col", "NULL")?.toInt() ?: 5

        //textView that contains the 2D matrix to be displayed
        val matrixTextView = findViewById<TextView>(R.id.speedNumberTextView)
        matrixTextView.movementMethod = ScrollingMovementMethod()
        matrixTextView.text = convert2DArrayToString(generateRandomNumbers(rows, cols))

        //auto sizing text based on the size of array
        matrixTextView.setAutoSizeTextTypeUniformWithConfiguration(32, 128, 2, 1)

        Toast.makeText(this, matrixTextView.textSize.toString(), Toast.LENGTH_LONG ).show()
        //button that user presses to fill out their answer
        val clearButton = findViewById<Button>(R.id.clearSpeedNumbersButton)
        clearButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.clearSpeedNumbersButton->{
                val numberTextView = findViewById<TextView>(R.id.speedNumberTextView)
                numberTextView.visibility = View.GONE

                val editNumberTextView = findViewById<EditText>(R.id.numberInputEditText)
                editNumberTextView.visibility = View.VISIBLE
            }

        }
    }

    /*
        generates 2D array with random 1 digit numbers filling each element
     */
    private fun generateRandomNumbers(rows : Int, col : Int) : Array<IntArray>{
        var result = Array(rows) {IntArray(col){0} }
        for(i in 0 until rows){
            for(j in 0 until col){
                result[i][j] = (0..9).random()
            }
        }
        return result
    }

    /*
        Makes a string of 2D matrix to be displayed in a textView
     */
    private fun convert2DArrayToString(array : Array<IntArray>) : String{
        var result = ""
        for(i in array.indices){
            for(j in array[i].indices){
                result += array[i][j].toString() + " "
            }
            result += "\n"
        }
        return result
    }
}
