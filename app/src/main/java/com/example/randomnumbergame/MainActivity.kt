package com.example.randomnumbergame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var randomNumber = Random.nextInt(0, 20)
    var tries = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val scoreText = findViewById<TextView>(R.id.score)
        val triesText = findViewById<TextView>(R.id.tries)
        val infoText = findViewById<TextView>(R.id.info)

        button.setOnClickListener() {
            if (editText.text.toString() == "") {
                return@setOnClickListener
            }
            this.tries++
            var guess = editText.text.toString().toInt()
            check(guess, triesText, scoreText, infoText)
            editText.text.clear()
        }
    }

    fun check(guess: Int, triesText: TextView, scoreText: TextView, infoText: TextView){
        if (guess < 0 || guess > 20) {
            infoText.text = "Random number is between 0 and 20"
            this.tries--
        } else if (guess == randomNumber) {
            points()
            infoText.text = "Guess a number"
            this.tries = 0
            randomNumber = Random.nextInt(0, 20)
        } else if (tries == 10) {
            infoText.text = "Too many tries. Play again"
            this.tries = 0
            randomNumber = Random.nextInt(0, 20)
        } else if (guess < randomNumber) {
            infoText.text = "Number is higher than " + guess.toString()
        } else if (guess > randomNumber) {
            infoText.text = "Number is lower than " + guess.toString()
        } else {
            infoText.text = "Error"
        }

        triesText.text = "Tries: " + tries.toString()
        scoreText.text = "Score: " + score.toString()
    }

    fun points() {
        var points = 5
        when (this.tries) {
            1-> points = 5
            2-> points = 3
            3-> points = 3
            4-> points = 3
            5-> points = 2
            6-> points = 2
            7-> points = 1
            8-> points = 1
            9-> points = 1
            10-> points = 1
        }
        this.score += points
        dialogShow(points)
    }

    fun dialogShow(points: Int){
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Congratulations!")
        builder.setMessage("You guessed a number in " + this.tries + " tries. You receive: $points points")
        builder.setPositiveButton("Play again"){ dialogInterface: DialogInterface, i: Int -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
