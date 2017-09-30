package com.tylorstech.unitconverterkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

/* Kotlin bugs me because you don't end statements with a semicolon. */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createSpinnerItems()
        button.onClick{ onGetResultButtonClicked() }
        resultsTextView.visibility = View.INVISIBLE
    }

    protected fun createSpinnerItems(){
        val strings = java.util.ArrayList<String>()
        strings.add("Fahrenheit")
        strings.add("Celsius")
        spinner_from.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, strings)
        spinner_to.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, strings)
        spinner_to.setSelection(1) //set to celsius
    }

    /*set in XML file*/
    fun onGetResultButtonClicked() {
        val txt = editText.getText().toString()
        if (txt.isEmpty()) {
            toast("You must enter a number to convert.")
            resultsTextView.visibility = View.INVISIBLE
            return
        }

        val i = java.lang.Double.parseDouble(txt)
        val from = spinner_from.selectedItem.toString()
        val to = spinner_to.selectedItem.toString()
        if (from == (to)) {
            toast("You can't convert between the same units.")
            resultsTextView.visibility = View.INVISIBLE
            return
        }
        val result = convert(from, to, i)

        resultsTextView.setText("Result: " + "%.2f".format(result) + " degrees")
        resultsTextView.visibility = View.VISIBLE
    }

    /*I'd rather pass enums, but I don't feel like converting strings to enums, and I can't be bothered to do anything else*/
    fun convert(from: String, to: String, num: Double): Double {
        if (from == to) return num
        return if (from == "Fahrenheit") (num - 32.0) * (5.0 / 9.0) else num * (9.0 / 5.0) + 32.0
    }
}
