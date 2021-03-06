package com.dani_chuks.andeladeveloper.starwars.base.mvi

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun View.clicks() = callbackFlow {
    setOnClickListener { offer(Unit) }
    awaitClose{ setOnClickListener(null) }
}

@ExperimentalCoroutinesApi
fun TextView.textChanges(): Flow<String?> = callbackFlow {
    val textWatcher = (object : TextWatcher {

        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.toString()?.let { offer(it) }
        }

    })
    addTextChangedListener(textWatcher)
    awaitClose { removeTextChangedListener(textWatcher)}
}
