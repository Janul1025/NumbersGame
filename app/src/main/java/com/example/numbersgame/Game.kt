package com.example.mygame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView


class Game : AppCompatActivity() {

    //Initialize variables
    var expression1: String = ""
    var expression2: String = ""
    var correctCount = 0
    var wrongCount = 0
    var time: Long = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onResume() {
        super.onResume()
        //Initialize components
        val eq1Txt = findViewById<TextView>(R.id.Eq1)
        val eq2Txt = findViewById<TextView>(R.id.Eq2)
        val ansTxt = findViewById<TextView>(R.id.Answer)
        val timeTxt = findViewById<TextView>(R.id.Time)
        val greaterBtn = findViewById<Button>(R.id.greaterBtn)
        val equalBtn = findViewById<Button>(R.id.equalBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        val newExpression = CreateExpression()
        val solveExp = Solve()

        //Generate new expression
        if (expression1.length <= 0 || expression2.length <= 0) {
            expression1 = newExpression.generate()
            expression2 = newExpression.generate()
        }

        //Display expressions
        eq1Txt.setText(expression1)
        eq2Txt.setText(expression2)

        //If Greater than
        greaterBtn.setOnClickListener {

            //Solve equation
            val ans1 = solveExp.solve(expression1)
            val ans2 = solveExp.solve(expression2)

            //Compare
            if (ans1 > ans2) {
                ansTxt.setText("CORRECT ANSWER!")
                ansTxt.setTextColor(Color.GREEN)
                correctCount++
                if (correctCount > 0 && correctCount % 5 == 0) {
                    time += 10
                }

            } else {
                //Wrong
                ansTxt.setText("WRONG ANSWER!")
                ansTxt.setTextColor(Color.RED)
                wrongCount++
            }

            //New value
            expression1 = newExpression.generate()
            expression2 = newExpression.generate()
            eq1Txt.setText(expression1)
            eq2Txt.setText(expression2)

        }

        //Equal
        equalBtn.setOnClickListener {

            //Solve
            val ans1 = solveExp.solve(expression1)
            val ans2 = solveExp.solve(expression2)

            //Compare
            if (ans1 == ans2) {
                ansTxt.setText("CORRECT!")
                ansTxt.setTextColor(Color.GREEN)
                correctCount++
                if (correctCount > 0 && correctCount % 5 == 0) {
                    time += 10
                }

            } else {
                ansTxt.setText("WRONG!")
                ansTxt.setTextColor(Color.RED)
                wrongCount++
            }

            //New value
            expression1 = newExpression.generate()
            expression2 = newExpression.generate()
            eq1Txt.setText(expression1)
            eq2Txt.setText(expression2)

        }
 //less than
        lessBtn.setOnClickListener {

            //Solve
            val ans1 = solveExp.solve(expression1)
            val ans2 = solveExp.solve(expression2)

            //Compare answers
            if (ans1 < ans2) {
                ansTxt.setText("CORRECT!")
                ansTxt.setTextColor(Color.GREEN)
                correctCount++
                if (correctCount > 0 && correctCount % 5 == 0) {
                    time += 10
                }

            } else {
                ansTxt.setText("WRONG!")
                ansTxt.setTextColor(Color.RED)
                wrongCount++
            }

            //New value
            expression1 = newExpression.generate()
            expression2 = newExpression.generate()
            eq1Txt.setText(expression1)
            eq2Txt.setText(expression2)

        }

        //Set time
        val timer = object : CountDownTimer(100000000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                //Display time
                timeTxt.setText(time.toString())
                time -= 1

                if (time < 0) {
                    //Hide other components
                    eq2Txt.setVisibility(View.GONE)
                    equalBtn.setVisibility(View.GONE)
                    greaterBtn.setVisibility(View.GONE)
                    lessBtn.setVisibility(View.GONE)

                    timeTxt.setText("Game Over!")
                     eq1Txt.setText("Correct: " + correctCount)
                    ansTxt.setText("Wrong: " + wrongCount)

                    eq1Txt.setTextColor(Color.GREEN)
                    ansTxt.setTextColor(Color.RED)
                    cancel();
                }
            }

            override fun onFinish() {
                finish()
            }
        }
        timer.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("expression1", expression1)
        outState.putCharSequence("expression2", expression2)
        outState.putLong("time", time)
        outState.putInt("correctCount", correctCount)
        outState.putInt("wrongCount", wrongCount)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        expression1 = savedInstanceState.getCharSequence("expression1") as String;
        expression2 = savedInstanceState.getCharSequence("expression2") as String;
        time = savedInstanceState.getLong("time")
        correctCount = savedInstanceState.getInt("correctCount")
        wrongCount = savedInstanceState.getInt("wrongCount")

    }
}