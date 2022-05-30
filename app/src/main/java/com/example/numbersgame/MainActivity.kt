package com.example.mygame

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Buttons
        val btnNewGame = findViewById<Button>(R.id.btnNewGame)
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        //Click About button
        btnAbout.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("About")
            alertBuilder.setMessage("20200472 - Janul Sellahewa" + "\n" +
            "I confirm that I understand what plagiarism is and have read and " +
            "understood the section on Assessment Offences in the Essential " +
            "Information for Students. The work that I have submitted is " +
            "entirely my own. Any work from other authors is duly referenced " +
            "and acknowledged."
            )
            alertBuilder.setPositiveButton("Done", null);
            alertBuilder.show()
        }

        //New game
        btnNewGame.setOnClickListener {
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        }

    }
}

