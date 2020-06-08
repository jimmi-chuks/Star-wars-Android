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

fun <S, A> storeIntent(block: S.() -> S): StoreResult<S, A> =
        StoreResult(intent { block() })

fun <S: Any, A> storeActionFromState(block: (S) -> A): StoreResult<S, A> {
    var state: S? = null
    val inn = intent<S> {
        checkNotNull(this){ "State can not be null" }
        state = this
        this
    }
    return StoreResult(inn, block(state!!))
}

fun <S, A> storeAction(block: () -> A): StoreResult<S, A> {
    return StoreResult(noChange(), block())
}

