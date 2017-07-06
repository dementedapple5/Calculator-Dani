package com.example.dani.calculator

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Build
import android.text.Spanned



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buPer.text = fromHtml("x<sup><small>y</small></sup>")
    }

    fun fromHtml(source: String): Spanned {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            return Html.fromHtml(source)
        }
    }

            protected fun buNumberEvent(view: View){
        val buttonSound = MediaPlayer.create(this, R.raw.multimedia_button_click_004)
        buttonSound.start()
        if (newOp) etResult.setText("")
        newOp = false
        val buSelected: Button = view as Button
        var auxStr: String = etResult.text.toString()
        when(buSelected.id){

            bu0.id -> auxStr+="0"
            bu1.id -> auxStr+="1"
            bu2.id -> auxStr+="2"
            bu3.id -> auxStr+="3"
            bu4.id -> auxStr+="4"
            bu5.id -> auxStr+="5"
            bu6.id -> auxStr+="6"
            bu7.id -> auxStr+="7"
            bu8.id -> auxStr+="8"
            bu9.id -> auxStr+="9"

            buC.id -> auxStr=""

            buDot.id -> auxStr+="."

            buPlMin.id -> auxStr="-"+auxStr

        }
        etResult.setText(auxStr)
    }

    var op = "*"
    var oldNumber = ""
    var newOp = false
    protected fun buOpEvent(view: View){
        val buttonSound = MediaPlayer.create(this, R.raw.multimedia_button_click_004)
        buttonSound.start()
        val buSelected: Button = view as Button
        when(buSelected.id) {
            buPlus.id -> {
                op = "+"
            }
            buMin.id -> {
                op = "-"
            }
            buMult.id -> {
                op = "*"
            }
            buDiv.id -> {
                op = "/"
            }
            buPer.id -> {
                op = "**"
            }
        }
        oldNumber = etResult.text.toString()
        newOp=true
    }

    protected fun buEqEvent(view: View){
        val buttonSound = MediaPlayer.create(this, R.raw.multimedia_button_click_004)
        buttonSound.start()
        val buSelected: Button = view as Button
        var newNumber: Double = etResult.text.toString().toDouble()
        when(op) {
            "*" -> newNumber*=oldNumber.toDouble()
            "+" -> newNumber+=oldNumber.toDouble()
            "-" -> newNumber=oldNumber.toDouble()-newNumber
            "/" -> newNumber=oldNumber.toDouble()/newNumber
            "**" -> newNumber=Math.pow(oldNumber.toDouble(),newNumber)
        }
        etResult.setText(newNumber.toString())
    }

}
