package com.dani_chuks.andeladeveloper.starwars.base.mvi

interface Intent<S> {
    fun reduce(oldState: S): S
}

fun <S> intent(block: S.() -> S): Intent<S> = object : Intent<S> {
    override fun reduce(oldState: S): S = block(oldState)
}

fun <S> noChange(): Intent<S> = intent<S> { this }

fun <S> sideEffect(block: S.() -> Unit): Intent<S> = object : Intent<S> {
    override fun reduce(oldState: S): S = oldState.apply(block)
}


