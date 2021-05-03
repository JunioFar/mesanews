package com.example.android.mesanews.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.android.mesanews.R

fun closeKeyBoard(view: View){
    val manager: InputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(view.windowToken,0)
}

fun verifyEditTextIsNotEmpty(vararg editTexts: EditText): Boolean {
    for (item in editTexts){
        if (item.text.isEmpty()) {
            item.requestFocus()
            item.error = item.context.getString(R.string.empty_field_error)
            return false
        }
    }
    return true
}