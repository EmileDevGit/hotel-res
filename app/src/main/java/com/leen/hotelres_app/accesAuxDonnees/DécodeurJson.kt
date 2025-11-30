package com.leen.hotelres_app.accesAuxDonnees

import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.Locale

class DécodeurJson {
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun decodeHistorique(json: String): List<Reservation> {
        val arr = JSONArray(json)
        val list = mutableListOf<Reservation>()

        for (i in 0 until arr.length()) {
            val obj = arr.getJSONObject(i)

            val dateDebut = sdf.parse(obj.getString("date_debut"))?.time ?: 0L
            val dateFin = sdf.parse(obj.getString("date_fin"))?.time ?: 0L

            val prix = obj.getDouble("prix").toFloat()

            list.add(
                Reservation(
                    numero = obj.getInt("id").toString(),
                    dateDébut = dateDebut,
                    dateFin = dateFin,
                    dateReservation = obj.getString("date_reservation"),
                    prix = prix,
                    nbPersonnes = null
                )
            )
        }

        return list
    }

    fun decodeChambres(json: String): List<Chambre> {
        val arr = JSONArray(json)
        val list = mutableListOf<Chambre>()

        for (i in 0 until arr.length()) {
            val obj = arr.getJSONObject(i)

            val numero = obj.optString("numero", "101")
            val nombreLits = obj.optInt("nombreLits", 2)
            val nombrePersonnes = obj.optInt("nombrePersonnes", 2)
            val inclusions = obj.optString("inclusions", "")
            val description = obj.optString("description", "")
            val suite = obj.optString("suite", "")
            val prix = obj.optDouble("prix", 0.0).toFloat()

            val imagesJson = obj.optJSONArray("images")
            val images = if (imagesJson != null) {
                Array(imagesJson.length()) { idx -> imagesJson.getString(idx) }
            } else {
                arrayOf(
                    "https://picsum.photos/800/400?image=10",
                    "https://picsum.photos/800/400?image=20",
                    "https://picsum.photos/800/400?image=30",
                    "https://picsum.photos/800/400?image=40",
                    "https://picsum.photos/800/400?image=50"
                )
            }

            list.add(
                Chambre(
                    numero = numero,
                    nombreLits = nombreLits,
                    nombrePersonnes = nombrePersonnes,
                    inclusions = inclusions,
                    description = description,
                    images = images,
                    suite = suite,
                    prix = prix
                )
            )
        }

        return list
    }
}