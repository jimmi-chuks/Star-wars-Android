package com.dani_chuks.andeladeveloper.starwars.data.db.repository

sealed class DaoPredicate
data class GetAllAlphabeticallyBySize(val size: Int): DaoPredicate()
data class GetAllBySize(val size: Int): DaoPredicate()
object GetAll: DaoPredicate()
object GetAllAlphabetically: DaoPredicate()
