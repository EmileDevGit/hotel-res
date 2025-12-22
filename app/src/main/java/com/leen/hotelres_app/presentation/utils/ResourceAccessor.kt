package com.leen.hotelres_app.presentation.utils

import android.content.Context


// permet d'accèes aux ressources (R.strings par exemple ) depuis d'autres contextes
class ResourceAccessor (private val context: Context) : IRessourceAccessor {
    override fun getString(ressourceId: Int): String {
        return  context.getString(ressourceId)
    }
}