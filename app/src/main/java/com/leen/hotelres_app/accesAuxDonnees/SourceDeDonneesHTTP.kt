package com.leen.hotelres_app.accesAuxDonnees

import android.util.Log
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

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
        response.close()

        if (!response.isSuccessful) {
            throw IOException("Erreur HTTP : ${response.code}")
        }

        return decodeurJSON.decodeHistorique(bodyString)
    }


    fun recupererChambres(): List<Chambre> {
        val urlChambres = "http://idefix.dti.crosemont.quebec:10170/chambres/disponibilite"
        Log.d("HTTP-REQUEST", urlChambres)

        val request = Request.Builder()
            .url(urlChambres)
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = client.newCall(request).execute()
        val bodyString = response.body?.string() ?: "[]"
        Log.d("CHAMBRES-HTTP", "Body: $bodyString")
        response.close()
        Log.d("CHAMBRES-HTTP", "HTTP code: ${response.code}")

        if (!response.isSuccessful) {
            throw IOException("Erreur HTTP : ${response.code}")
        }

        return decodeurJSON.decodeChambres(bodyString)
    }


    fun creerReservation(reservation: Reservation): Boolean {
        val urlReservation = "http://idefix.dti.crosemont.quebec:10170/reservations"
        Log.d("HTTP-REQUEST", urlReservation)

        val json = JSONObject().apply {
            //put("locataire_id", reservation.locataireId)
            put("chambre_id", reservation.numero)
            put("date_debut", reservation.dateDébut)
            put("date_fin", reservation.dateFin)
            put("prix", reservation.prix)
            put("nb_personnes", reservation.nbPersonnes)
        }

        val requestBody = json.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(urlReservation)
            .post(requestBody)
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = client.newCall(request).execute()
        Log.d("RESERVATION-HTTP", "HTTP code: ${response.code}")
        val bodyString = response.body?.string()
        Log.d("RESERVATION-HTTP", "Body: $bodyString")
        response.close()

        return response.isSuccessful
    }
}