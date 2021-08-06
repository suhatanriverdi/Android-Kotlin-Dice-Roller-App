package com.example.diceroller

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // When the button is tapped, roll a dice and update the UI
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        // Create a new dice object with 6 sides
        val dice = Dice()

        // Roll the dice and get a random number between 1..6
        val diceRoll = dice.roll()
        val diceRoll2 = dice.roll()

        // Find the imageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = getDrawableResource(diceRoll)
        val drawableResource2 = getDrawableResource(diceRoll2)

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)
        diceImage2.setImageResource(drawableResource2)

        // Update the contest description
        diceImage.contentDescription = diceRoll.toString()
        diceImage2.contentDescription = diceRoll2.toString()

        // If two dices have same face values
        if (diceRoll == diceRoll2) {
            val test = "You Won!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, test, duration)
            toast.setGravity(Gravity.TOP, 0, 400)
            toast.show()
        }
    }

    private fun getDrawableResource(diceRoll: Int) = when (diceRoll) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}