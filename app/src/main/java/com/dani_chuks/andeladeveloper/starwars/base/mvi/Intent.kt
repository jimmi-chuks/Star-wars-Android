package com.dani_chuks.andeladeveloper.starwars.base.mvi

interface Intent<S> {
    fun reduce(oldState: S): S
}

fun <S> intent(block: S.() -> S): Intent<S> = object : Intent<S> {
    override fun reduce(oldState: S): S = block(oldState)
}

fun <S> noChange(): Intent<S> = intent<S> { this }

//fun <S> sideEffect(block: S.() -> Unit): Intent<S> = object : Intent<S> {
//    override fun reduce(oldState: S): S = oldState.apply(block)
//}

//For Foo<out T : TUpper>, where T is a covariant type parameter with the upper bound TUpper,
// Foo<*> is equivalent to Foo<out TUpper>. It means that when the T is unknown
// you can safely read values of TUpper from Foo<*>.
//
// For Foo<in T>, where T is a contravariant type parameter,
// Foo<*> is equivalent to Foo<in Nothing>. It means there is nothing
// you can write to Foo<*> in a safe way when T is unknown.

//For Foo<T : TUpper>, where T is an invariant type parameter with the upper bound TUpper,
// Foo<*> is equivalent to Foo<out TUpper> for reading values and to Foo<in Nothing> for writing values.
