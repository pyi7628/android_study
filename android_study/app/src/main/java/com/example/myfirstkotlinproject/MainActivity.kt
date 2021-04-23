package com.example.myfirstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        saveData(heightEditText.text.toString().toInt(),
        weightEditText.text.toString().toInt())

        loadData()

        resultButton.setOnClickListener{
            //val intent = Intent(this, ResultActivity::class.java)
            //startActivity(intent)
            //아래랑 같은거!
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }

    }


    private fun saveData(height:  Int, weight: Int)
    {
        val pref =PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData()
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0)
        {
            heightEditText.setText(height.toInt())
            weightEditText.setText(weight.toInt())
        }
    }
}