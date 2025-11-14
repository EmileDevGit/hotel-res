package com.leen.hotelres_app.Model

import java.text.SimpleDateFormat
import java.util.Locale

class FormulaireReservationModel {

    fun getReservation() : Reservation{

        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)

        return Reservation(
            "Chambre pour 2 personnes",
            sdf.parse("14 oct. 2025")?.time ?: 0L,
            sdf.parse("27 oct. 2025")?.time ?: 0L,
            "10 oct. 2025",
            "130$/par nuit",
            3
        )
    }
}