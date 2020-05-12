package com.example.mentaltraining

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.trimmedLength
import androidx.core.widget.TextViewCompat
import androidx.preference.PreferenceManager
import kotlin.math.absoluteValue


class SpeedNumbersActivity : AppCompatActivity(), View.OnClickListener {

    var mAnswerText : String = ""
    var mInputText :String = ""
    var mTextSize : Float = 5F

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
        mInputText = convert2DArrayToString(generateRandomNumbers(rows, cols))
        matrixTextView.text = mInputText
        mTextSize = when(cols) {
            5-> 105F
            10-> 50F
            15-> 33F
            20-> 25F
            else->{10F}
        }
        matrixTextView.textSize = mTextSize

        //for debug, change text size based on column size categories

        //auto sizing text based on the size of array
        //matrixTextView.setAutoSizeTextTypeUniformWithConfiguration(32, 128, 2, 1)

        //Toast.makeText(this, matrixTextView.textSize.toString(), Toast.LENGTH_LONG ).show()
        //button that user presses to fill out their answer
        val clearButton = findViewById<Button>(R.id.clearSpeedNumbersButton)
        clearButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var answerTextView = findViewById<TextView>(R.id.speed_numbers_answer_text)
        when(v?.id) {
            R.id.clearSpeedNumbersButton->{

                setContentView(R.layout.speed_numbers_answer_layout)
                answerTextView = findViewById<TextView>(R.id.speed_numbers_answer_text)
                //setting onClickListener for all the buttons (trying to move this to separate function
                var calculatorButton = findViewById<Button>(R.id.calculator_seven)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_eight)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_nine)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_clear)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_four)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_five)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_six)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_sign)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_one)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_two)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_three)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_empty)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_zero)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_delete)
                calculatorButton.setOnClickListener(this)
                calculatorButton = findViewById<Button>(R.id.calculator_enter)
                calculatorButton.setOnClickListener(this)
            }
            //Answer View buttons
            R.id.calculator_seven -> mAnswerText += "7 "
            R.id.calculator_eight -> mAnswerText += "8 "
            R.id.calculator_nine  -> mAnswerText += "9 "
            R.id.calculator_clear -> mAnswerText = ""
            R.id.calculator_four  -> mAnswerText += "4 "
            R.id.calculator_five  -> mAnswerText += "5 "
            R.id.calculator_six   -> mAnswerText += "6 "
            //R.id.calculator_sign  -> mAnswerText.
            R.id.calculator_one   -> mAnswerText += "1 "
            R.id.calculator_two   -> mAnswerText += "2 "
            R.id.calculator_three -> mAnswerText += "3 "
            R.id.calculator_empty -> mAnswerText += "\n"
            R.id.calculator_zero -> mAnswerText += "0 "
            R.id.calculator_delete-> mAnswerText = mAnswerText.dropLast(2)
            R.id.calculator_enter -> {
                mAnswerText += "/n"
                checkAnswer()
                mAnswerText = mInputText
            }
        }
        if(answerTextView != null ){
            answerTextView.text = mAnswerText
            answerTextView.textSize = mTextSize
            Toast.makeText(this, answerTextView.isVerticalScrollBarEnabled.toString() , Toast.LENGTH_LONG ).show()
        }
    }

    /*
        -Checks answer against the given matrix
        -Changes the display text to original matrix with the answer you got wrong in red
        -TODO spacing could be weird
     */
    private fun checkAnswer() {
        //loop through and compare the strings
        var wrongIndices = arrayListOf<Int>()   //keeps track of wrong indices to change their color later
        val finalAnswerString = padAnswer()
        val a = mInputText.length
        val b = finalAnswerString.length
        val c = mAnswerText.length
        for(i in 0 until mInputText.length){
            if(mInputText[i] != finalAnswerString[i]){
                Toast.makeText(this,"Not Correct", Toast.LENGTH_LONG ).show()
                wrongIndices.add(i)
            }
        }
    }
    //pads answer text to be the size of input text
    private fun padAnswer() : String {
        if(mAnswerText.length == mInputText.length){
            return mAnswerText
        }
        val difference = mAnswerText.length - mInputText.length
        return if(difference < 0){
            mAnswerText.padEnd(mInputText.length,'a')
        } else{
            mInputText.padEnd(mAnswerText.length, 'a')
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

    private fun setUpButtonOnClickListeners() {

    }
}
