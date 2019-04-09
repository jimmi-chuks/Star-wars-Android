package com.dani_chuks.andeladeveloper.starwars.data.db.remote

import com.dani_chuks.andeladeveloper.starwars.di.Result
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Result.Error(IOException(errorMessage, e))
    }
}
