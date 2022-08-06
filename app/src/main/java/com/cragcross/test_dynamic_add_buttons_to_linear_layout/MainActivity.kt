package com.cragcross.test_dynamic_add_buttons_to_linear_layout

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    //
    // Auxiliary buttons
    //

    // Difficulty row buttons
    lateinit var btnEasy: Button
    lateinit var btnMedium: Button
    lateinit var btnHard: Button

    // Reset Help row buttons
    lateinit var btnReset: Button
    lateinit var btnHelp: Button


//    lateinit var layouts : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"


        val llGame = layoutGame(this)

        val llAuxiliaries = layoutAuxiliaries(this)

        val flWholePage = buildFrameLayout(this, llGame, llAuxiliaries)

        setContentView(flWholePage)


        testAuxiliaryButtonListeners()

    }

    private fun testAuxiliaryButtonListeners() {
        // Auxiliary Button listeners

        // Difficulty buttons
        btnEasy.setOnClickListener {
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked EASY.", Toast.LENGTH_SHORT).show()
        }
        btnMedium.setOnClickListener {
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked MEDIUM.", Toast.LENGTH_SHORT).show()
        }
        btnHard.setOnClickListener {
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked HARD.", Toast.LENGTH_SHORT).show()
        }

        // Reset - Help buttons
        btnReset.setOnClickListener {
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked RESET.", Toast.LENGTH_SHORT).show()
        }
        btnHelp.setOnClickListener {
            // your code to perform when the user clicks on the button
            Toast.makeText(this@MainActivity, "You clicked HELP.", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
//        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun buildFrameLayout(
        context: Context,
        llGame: LinearLayout,
        llAuxiliaries: LinearLayout
    ): FrameLayout {

        val frameLayout = FrameLayout(context)
        val gameFrameLayout = FrameLayout(context)
        val auxiliariesFrameLayout = FrameLayout(context)

        // Deal with Game --- set to top of page
        gameFrameLayout.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER_HORIZONTAL
        )

        gameFrameLayout.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.purple_200
            )
        )

        gameFrameLayout.addView(llGame, gameFrameLayout.layoutParams)


        // Deal with Auxiliaries --- set to bottom of page
        auxiliariesFrameLayout.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT ,
            Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        )

        auxiliariesFrameLayout.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.purple_700
            )
        )

        auxiliariesFrameLayout.addView(llAuxiliaries, auxiliariesFrameLayout.layoutParams)

        frameLayout.addView(gameFrameLayout)
        frameLayout.addView(auxiliariesFrameLayout)

        return frameLayout
    }

    fun layoutGame(context: Context): LinearLayout {
        val config = resources.configuration
        val screenWidth = config.screenWidthDp
        val screenHeight = config.screenHeightDp
        val screenHeightToWidthRatio = (screenHeight * 1.0) / screenWidth

        val marginSeparation = 20

        val tileHeight = 100
        val tileWidth = (tileHeight / screenHeightToWidthRatio).roundToInt() + 45

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

//        layout.setBackgroundColor(Color.parseColor("#3c3f41"));

        for (r in 0..3) {
            val row = LinearLayout(this)
            row.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
                600,
                300
            )
//            (row.layoutParams as LinearLayout.LayoutParams).setMargins(60, 20,30, 10)
            layout.gravity = Gravity.CENTER
            for (c in 0..3) {
                val btn = Button(this)
                btn.layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    tileWidth,
//                    LinearLayout.LayoutParams.MATCH_PARENT
                    tileHeight
                )

                (btn.layoutParams as LinearLayout.LayoutParams).setMargins(
                    marginSeparation,
                    marginSeparation,
                    marginSeparation,
                    marginSeparation
                )
//                btn.text = "Button " + (j + 1 + i * 4)
                val tag = (c + 1 + r * 4)
                btn.text = "" + tag
                btn.id = tag
                btn.setBackgroundColor((Color.CYAN))


//                btn.setBackgroundResource(R.drawable.border_button);

                btn.width = 100

                row.addView(btn)
            }
            layout.addView(row)
        }

//        setContentView(layout)

        return layout

    }

    fun layoutAuxiliaries(context: Context): LinearLayout {

        val auxiliariesLayout = LinearLayout(context)
        auxiliariesLayout.orientation = LinearLayout.VERTICAL

        val rowLayoutDifficulties = buildDifficultyRowLayout(context)
        val rowLayoutResetHelp = buildResetHelpRowLayout(context)


        auxiliariesLayout.addView(rowLayoutDifficulties)
        auxiliariesLayout.addView(rowLayoutResetHelp)

        return auxiliariesLayout
    }


    private fun buildDifficultyRowLayout(context: Context): LinearLayout {


        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.HORIZONTAL


        //
        // Row: Easy Medium Hard
        //

        val btn_id_easy = 100
        val btn_id_medium = 101
        val btn_id_hard = 102


        val rowDifficulties = LinearLayout(this)


        rowDifficulties.layoutParams = LinearLayout.LayoutParams(
            800,
            300
        )

        rowDifficulties.gravity = Gravity.CENTER


        // Easy
        btnEasy = makeAuxiliaryButton(context, btn_id_easy, "Easy", R.color.difficulty_button_easy)
        rowDifficulties.addView(btnEasy)

        // Medium
        btnMedium =
            makeAuxiliaryButton(context, btn_id_medium, "Medium", R.color.difficulty_button_medium)
        rowDifficulties.addView(btnMedium)

        // Hard
        btnHard = makeAuxiliaryButton(context, btn_id_hard, "Hard", R.color.difficulty_button_hard)
        rowDifficulties.addView(btnHard)


        layout.addView(rowDifficulties)


        return layout
    }

    private fun buildResetHelpRowLayout(context: Context): LinearLayout {
//        val marginSeparation = 20

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.HORIZONTAL


        //
        // Row: Reset      Help
        //


        val btn_id_reset = 200
        val btn_id_help = 201

        val rowResetHelp = LinearLayout(this)


        rowResetHelp.layoutParams = LinearLayout.LayoutParams(
            800,
            300
        )

        rowResetHelp.gravity = Gravity.CENTER


        // Reset
        btnReset =
            makeAuxiliaryButton(context, btn_id_reset, "Reset", R.color.reset_help__button_reset)
        rowResetHelp.addView(btnReset)

        // Help
        btnHelp = makeAuxiliaryButton(context, btn_id_help, "Help", R.color.reset_help__button_help)
        rowResetHelp.addView(btnHelp)


        layout.addView(rowResetHelp)



        return layout
    }

    private fun makeAuxiliaryButton(
        context: Context,
        buttonId: Int,
        buttonText: String,
        buttonColourId: Int
    ): Button {
        val marginSeparation = 20

        val difficultyBtnWidth = 200
        val difficultyBtnHeight = 100

        val btn = Button(this)
        btn.layoutParams = LinearLayout.LayoutParams(
            difficultyBtnWidth,
            difficultyBtnHeight
        )

        (btn.layoutParams as LinearLayout.LayoutParams).setMargins(
            marginSeparation,
            marginSeparation,
            marginSeparation,
            marginSeparation
        )

        btn.id = buttonId
        btn.text = buttonText

        btn.setBackgroundColor(
            ContextCompat.getColor(
                context,
                buttonColourId
            )
        )

        return btn
    }


}
