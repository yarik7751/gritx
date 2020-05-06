package com.solbegsoft.gritx.tools.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(action: (text: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            action(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    })
}