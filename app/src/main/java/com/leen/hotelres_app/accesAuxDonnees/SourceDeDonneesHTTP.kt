package com.leen.hotelres_app.accesAuxDonnees

import android.util.Log
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.Request

class SourceDeDonneesHTTP(private val decodeurJSON: DécodeurJson= DécodeurJson()) {

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    // valide 30j
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0Iiwicm9sZSI6IkNMSUVOVCIsImFsZyI6IkhTMjU2IiwiZXhwIjoxNzY2OTgzMTgxfQ.mXzYjhKpn-qEmJEwKooNFLhsc-ko-cMDe6Iw18Dav40"


    fun recupererHistoriqueReservation(): List<Reservation> {
        val urlHistorique = "http://idefix.dti.crosemont.quebec:10170/reservations/"
        Log.d("HTTP-REQUEST", urlHistorique)

        val request = Request.Builder()
            .url(urlHistorique)
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = client.newCall(request).execute()
        val bodyString = response.body?.string() ?: "[]"
        Log.d("HISTO-HTTP", "Body: $bodyString")
        response.close()
        Log.d("HISTO-HTTP", "HTTP code: ${response.code}")

        if (!response.isSuccessful) {
            throw IOException("Erreur HTTP : ${response.code}")
        }

        return decodeurJSON.decodeHistorique(bodyString)
    }


    fun recupérerChambres(): List<Chambre> {
        val urlChambres = "http://idefix.dti.crosemont.quebec:10170/chambres/disponibilite"
        Log.d("HTTP-REQUEST", urlChambres)

        val request = Request.Builder()
            .url(urlChambres)
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = client.newCall(request).execute()
        val bodyString = response.body?.string() ?: "[]"
        Log.d("HISTO-HTTP", "Body: $bodyString")
        response.close()
        Log.d("HISTO-HTTP", "HTTP code: ${response.code}")

        if (!response.isSuccessful) {
            throw IOException("Erreur HTTP : ${response.code}")
        }

        return decodeurJSON.decodeChambres(bodyString)
    }
}