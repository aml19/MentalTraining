package com.example.mentaltraining

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/*
    -Sets onClickListener for every calculator button (calculator_layout.xml)
    -Handles when that button is clicked
 */
class Calculator : AppCompatActivity(), View.OnClickListener{
    init{
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

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}