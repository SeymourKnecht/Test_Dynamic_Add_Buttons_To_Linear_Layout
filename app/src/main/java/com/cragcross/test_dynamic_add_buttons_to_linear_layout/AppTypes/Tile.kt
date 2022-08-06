package com.cragcross.test_dynamic_add_buttons_to_linear_layout.AppTypes

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.widget.Button


class Tile {

    var button_: Button
    var tileFont_: Font? = null

    // See: https://www.baeldung.com/kotlin/constructors

     constructor (context: Context, id: Int) {
        button_ =  Button(context)
         button_.setText(id.toString())
         button_.setBackgroundColor((Color.CYAN))
         button_.setId(id)
//        button_.visibility
     }

//    public setTileAttributes ( c: Char) {
//        button_ = Button(context)
//
//    }
//
//    constructor(indexAsText: String?, fontSize: Int) {
//        tileFont_ = Font("Arial", Font.BOLD, fontSize) //this is a class function
//        button_ = Button(indexAsText)
////        button_.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK))
//        var tf: Typeface? = Typeface.createFromAsset(getAssets(), "Poppins-Bold.ttf")
//        button_.setTypeface(tileFont_)
//



//    public void SetFont(int fontSize)
//    {
//        tileFont_ = new Font("Arial", Font.BOLD, fontSize);
//    }

    //    public void SetFont(int fontSize)
    //    {
    //        tileFont_ = new Font("Arial", Font.BOLD, fontSize);
    //    }
    fun GetText(): CharSequence? {
        return button_.getText()
    }

//    fun Sing() {
//        OptionPane.showMessageDialog(null, "Hello from Tile!")
//    }
}