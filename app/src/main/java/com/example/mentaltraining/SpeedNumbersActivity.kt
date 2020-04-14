package com.example.mentaltraining

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat


class SpeedNumbersActivity : AppCompatActivity(), View.OnClickListener {

    //variable to hold 2 dimensional array of numbers to be displayed
    var mNumbers2DArray = arrayOf<IntArray>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speed_numbers_main_layout)

        //mNumbers2DArray = generateRandomNumbers(25, 20)
        //val temp = convert2DArrayToString(mNumbers2DArray)

        val textView = findViewById<TextView>(R.id.speedNumberTextView)
        textView.text = convert2DArrayToString(generateRandomNumbers(25, 20))
        textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM)

        val clearButton = findViewById<Button>(R.id.clearSpeedNumbersButton)
        clearButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val numberTextView = findViewById<TextView>(R.id.speedNumberTextView)
        numberTextView.visibility = View.GONE

        val editNumberTextView = findViewById<EditText>(R.id.numberInputEditText)
        editNumberTextView.visibility = View.VISIBLE
    }

    //generates 2D array with random 1 digit numbers filling each element
    private fun generateRandomNumbers(rows : Int, col : Int) : Array<IntArray>{
        var result = Array(rows) {IntArray(col){0} }
        for(i in 0 until rows){
            for(j in 0 until col){
                result[i][j] = (0..9).random()
            }
        }
        return result
    }

    private fun convert2DArrayToString(array : Array<IntArray>) : String{
        var result = ""
        for(i in array.indices){
            for(j in array[i]){
                result += array[i][j].toString() + " "
            }
            result += "\n"
        }
        return result
    }


}
