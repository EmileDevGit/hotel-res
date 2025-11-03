package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Model.Reservation
import java.text.SimpleDateFormat
import java.util.Locale

class FormulaireReservationsPresentateur {

    fun getReservation() : Reservation{

        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)

        return Reservation(
            "Chambre pour 2 personnes",
            sdf.parse("14 Oct 2025")?.time ?: 0L,
            sdf.parse("27 Oct 2025")?.time ?: 0L,
            "10 oct. 2025",
            "130$/par nuit",
            3
        )
    }
}