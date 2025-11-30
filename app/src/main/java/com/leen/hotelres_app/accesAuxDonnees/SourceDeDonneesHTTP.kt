package com.leen.hotelres_app.accesAuxDonnees

import android.util.Log
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import okhttp3.HttpUrl
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

    // Valide pour 30 jours
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

    fun recupererChambres(filtre: ChambreFiltre): List<Chambre> {

        val urlChambres = HttpUrl.Builder()
            .scheme("http")
            .host("idefix.dti.crosemont.quebec")
            .port(10170)
            .addPathSegments("chambres/disponibilite")
            .addQueryParameter("dateDebut", filtre.dateDebut.toString())
            .addQueryParameter("dateFin", filtre.dateFin.toString())
            .addQueryParameter("prix", filtre.prix.toString())
            .build()

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

    fun recupererChambresParNum(num: String): Chambre? {
        val urlChambres = "http://idefix.dti.crosemont.quebec:10170/chambres/${num}"
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

        return decodeurJSON.decodeChambreParNumero(bodyString, num)
    }

    // Conversion des dates Long en Date
    fun Long.toBackendDate(): String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
        return sdf.format(java.util.Date(this))
    }

    fun creerReservation(reservation: Reservation, chambre : Chambre): Boolean {
        val urlReservation = "http://idefix.dti.crosemont.quebec:10170/reservations"
        Log.d("HTTP-REQUEST", urlReservation)

        val json = JSONObject().apply {
            put("chambre_id", chambre.id)
            put("numero_appart", chambre.numero)
            put("nbre_lits", chambre.nombreLits)
            put("date_debut", reservation.dateDébut.toBackendDate())
            put("date_fin", reservation.dateFin.toBackendDate())
            put("dateDebut", reservation.dateDébut.toBackendDate())
            put("dateFin", reservation.dateFin.toBackendDate())
            put("prix", reservation.prix.toDouble())
            put("nb_personnes", reservation.nbPersonnes ?: 1)
            put("status", "EnAttente")
            put("locataire_id", "4")
            put("admin_id", "3")
        }

        val requestBody = json.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(urlReservation)
            .post(requestBody)
            .addHeader("Authorization", "Bearer $token")
            .build()

        client.newCall(request).execute().use { response ->
            Log.d("RESERVATION-HTTP", "HTTP code: ${response.code}")
            val bodyString = response.body?.string()
            Log.d("RESERVATION-HTTP", "Body: $bodyString")

            if (!response.isSuccessful) {
                throw IOException("Erreur HTTP : ${response.code} - $bodyString")
            }
            return true
        }
    }
}