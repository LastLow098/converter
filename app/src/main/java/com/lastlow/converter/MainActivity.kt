package com.lastlow.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var isDecEditView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTen = findViewById<EditText>(R.id.etTen)
        val editTwo = findViewById<EditText>(R.id.etTwo)

        editTen.setOnFocusChangeListener { view, b -> isDecEditView = b }

        editTen.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isDecEditView) {
                    var dec = -1
                    val decStr = p0.toString()
                    if (decStr.length in 1..8) {
                        dec = decStr.toInt()
                        if (dec >= 0) {
                            var bin = ""
                            while (dec != 0) {
                                bin = if (dec % 2 == 0) "0$bin" else "1$bin"
                                dec /= 2
                            }
                            editTwo.setText(bin)
                        }
                    } else {
                        editTwo.setText("")
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        editTwo.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!isDecEditView) {
                    val twoStr = p0.toString()
                    editTen.setText(if (twoStr.isNotEmpty()) twoStr.toInt(2).toString() else "")
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}