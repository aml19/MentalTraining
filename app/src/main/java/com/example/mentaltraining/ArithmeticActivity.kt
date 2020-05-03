package com.example.mentaltraining

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.preference.PreferenceManager
import kotlin.math.pow

class ArithmeticActivity : AppCompatActivity(), View.OnClickListener {

    //enum for the arithmetic type
    enum class OPERATION_TYPE (val value : Int) {

        NULL(0),
        ADDITION(1),
        SUBTRACTION(2),
        MULTIPLICATION(3),
        DIVISION(4);
    }

    var clear : Boolean = false

    private var operationType : OPERATION_TYPE = OPERATION_TYPE.ADDITION

    //global text fields
    private var mAnswerText : String = ""          //string that keeps track of the answer string to be displayed
    private var firstNumberString : String = ""          //stores first random number shown
    private var secondNumberString: String = ""          //stores seconds random number shown
    private var firstNumberInt : Int = 0
    private var secondNumberInt : Int = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arithmetic_main_layout)
        val preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)

        //setting operands
        val digits = preferences.getString("addition_digits_key", "NULL")?.toInt() ?: 3
        //Toast.makeText(this, preferences.getString("addition_digits_key", "NULL"), Toast.LENGTH_LONG ).show()
        firstNumberInt  = getRandomNumber(digits)
        secondNumberInt = getRandomNumber(digits)
        firstNumberString  = firstNumberInt.toString()
        secondNumberString = secondNumberInt.toString()

        //get category type
        val operationTypeString = preferences.getString(resources.getString(R.string.operation_type), "NULL")
        Log.d(ContentValues.TAG, "operationTypeString: $operationTypeString")

        when(operationTypeString){
            "Addition" -> {
                operationType = OPERATION_TYPE.ADDITION
                secondNumberString = "+ $secondNumberString"
            }
            "Subtraction" -> {
                operationType = OPERATION_TYPE.SUBTRACTION
                secondNumberString = "- $secondNumberString"
            }
            "Multiplication" -> {
                operationType = OPERATION_TYPE.MULTIPLICATION
                secondNumberString = "x $secondNumberString"
            }
            "Division" -> {
                operationType = OPERATION_TYPE.DIVISION
                secondNumberString = "÷ $secondNumberString"
            }
            else->{
                operationType = OPERATION_TYPE.NULL
                firstNumberString = ""
                secondNumberString = "No Category Selected"} //maybe make new activity or screen with nothing but this messsage/ dialog box
        }

        //setting text fields for the number fields
        val numberText = findViewById<TextView>(R.id.firstNumber)
        numberText.text = firstNumberString
        val secondNumberText = findViewById<TextView>(R.id.secondNumber)
        secondNumberText.text = (secondNumberString)

        //set onClickListeners for calculator buttons
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
        calculatorButton = findViewById<Button>(R.id.calculator_point)
        calculatorButton.setOnClickListener(this)
        calculatorButton = findViewById<Button>(R.id.calculator_zero)
        calculatorButton.setOnClickListener(this)
        calculatorButton = findViewById<Button>(R.id.calculator_delete)
        calculatorButton.setOnClickListener(this)
        calculatorButton = findViewById<Button>(R.id.calculator_enter)
        calculatorButton.setOnClickListener(this)
    }

    /*
        updates the display
     */
    override fun onClick(v: View?) {

        //clear answer when one of these buttons is pushed so you don't have to do it manually everytime
        if(clear){
            mAnswerText = ""
            clear = false
        }
        //ID of answer TetView to modify
        val answerTextView = findViewById<TextView>(R.id.answer)

        //modifies answer string accordingly
        when (v?.id){
            R.id.calculator_seven -> mAnswerText += "7"
            R.id.calculator_eight -> mAnswerText += "8"
            R.id.calculator_nine  -> mAnswerText += "9"
            R.id.calculator_clear -> mAnswerText = ""
            R.id.calculator_four  -> mAnswerText += "4"
            R.id.calculator_five  -> mAnswerText += "5"
            R.id.calculator_six   -> mAnswerText += "6"
            //R.id.calculator_sign  -> mAnswerText.
            R.id.calculator_one   -> mAnswerText += "1"
            R.id.calculator_two   -> mAnswerText += "2"
            R.id.calculator_three -> mAnswerText += "3"
            //R.id.calculator_empty
            R.id.calculator_point -> {      //make sure only one decimal point is in Answer
                if(!mAnswerText.contains('.'))
                    mAnswerText += "."
            }
            R.id.calculator_zero -> mAnswerText += "0"
            R.id.calculator_delete-> mAnswerText = mAnswerText.dropLast(1)
            R.id.calculator_enter -> {
                clear = true
                mAnswerText =
                    if (checkAnswer())
                        "Correct!"
                    else
                        "You Suck!"
            }
        }

        answerTextView.text = mAnswerText
    }

    private fun checkAnswer() : Boolean {
        if(mAnswerText == "") return false
        return when(operationType) {
            OPERATION_TYPE.ADDITION -> mAnswerText.toInt() == firstNumberInt + secondNumberInt
            OPERATION_TYPE.SUBTRACTION -> mAnswerText.toInt() == firstNumberInt - secondNumberInt
            OPERATION_TYPE.MULTIPLICATION -> mAnswerText.toInt() == firstNumberInt * secondNumberInt
            OPERATION_TYPE.DIVISION -> mAnswerText.toInt() == firstNumberInt / secondNumberInt     //no decimals for now
            else -> {
                false
            }
        }
        return false
    }

    //gets random number with specified digits
    private fun getRandomNumber(digits : Int) : Int {
        val range = 10.toDouble().pow(digits)
        return (1 until range.toInt()).random()
    }
}

