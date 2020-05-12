package com.example.mentaltraining

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

/*
    This activity parses which game to Start and has the settings interface for each game
 */

class SettingsActivity : AppCompatActivity(), View.OnClickListener {

    var mName = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)      //basic settings layout for everything

        //get category name from Main Activity
        mName = intent.getStringExtra("Name")

        //loads data
        val preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val edit = preferences.edit()

        //displays correct preferences depending on what Category was selected
        when(mName){
            resources.getString(R.string.arithmetic) -> {
                val operationTypePreference = preferences
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, ArithmeticSettingsFragment())
                    .commit()
            }
            resources.getString(R.string.speed_numbers) -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SpeedNumbersSettingsFragment())
                    .commit()
            }
            else -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, ArithmeticSettingsFragment())
                    .commit()
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //button that calls the specified game activity
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener(this)

        val test = findViewById<Button>(R.id.memory_card_start_button)
    }

    override fun onClick(v: View?) {
        //parse out which activity to go to
        when(mName){
            resources.getString(R.string.arithmetic) -> {
                val intent = Intent(this@SettingsActivity, ArithmeticActivity::class.java)
                startActivity(intent)
            }
            resources.getString(R.string.speed_numbers) -> {
                val intent = Intent(this@SettingsActivity, SpeedNumbersActivity::class.java)
                startActivity(intent)
            }
            resources.getString(R.string.speed_cards) -> {
                val intent = Intent(this@SettingsActivity, CardsActivity::class.java)
                startActivity(intent)
            }
            else -> {
                //TODO handle no activity to go to
            }
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }


    class ArithmeticSettingsFragment : PreferenceFragmentCompat() {
        //TODO filter out what to display based on operationType selected
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.arithmetic_preferences, rootKey)
            //gets operation preference value (Add,Sub, Div, Mult)
            val operationType = findPreference<ListPreference>("operation_type")
            setPreferenceCategoryVisibility(operationType?.entry.toString())
            operationType?.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, selectedValue ->
                    setPreferenceCategoryVisibility(selectedValue.toString())
                    true
                }
        }

        //Takes the preference name and sets the visibilty of Settings Activity to disclude other
        //preference categories
        private fun setPreferenceCategoryVisibility(name: String){
            val additionPrefCat = findPreference<PreferenceCategory>("addition")
            val subtractionPrefCategory = findPreference<PreferenceCategory>("subtraction")
            val multiplicationPrefCategory = findPreference<PreferenceCategory>("multiplication")
            val divisionPrefCategory = findPreference<PreferenceCategory>("division")
            when(name){
                "Addition" ->{
                    additionPrefCat?.isVisible = true
                    subtractionPrefCategory?.isVisible = false
                    multiplicationPrefCategory?.isVisible = false
                    divisionPrefCategory?.isVisible = false
                }
                "Subtraction"->{
                    additionPrefCat?.isVisible = false
                    subtractionPrefCategory?.isVisible = true
                    multiplicationPrefCategory?.isVisible = false
                    divisionPrefCategory?.isVisible = false
                }
                "Multiplication" ->{
                    additionPrefCat?.isVisible = false
                    subtractionPrefCategory?.isVisible = false
                    multiplicationPrefCategory?.isVisible = true
                    divisionPrefCategory?.isVisible = false
                }
                "Division" -> {
                    additionPrefCat?.isVisible = false
                    subtractionPrefCategory?.isVisible = false
                    multiplicationPrefCategory?.isVisible = false
                    divisionPrefCategory?.isVisible = true
                }
            }
        }
    }

    class SpeedNumbersSettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.speed_numbers_preferences, rootKey)
        }
    }

}