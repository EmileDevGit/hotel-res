package com.leen.hotelres_app.accesAuxDonnees

import com.leen.hotelres_app.presentation.model.Reservation
import java.text.SimpleDateFormat
import java.util.Locale

object SourceDeDonneesBidonFormulaire : SourceDeDonnees {

    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)

    var reservation = Reservation(
        "Chambre pour 2 personnes",
        sdf.parse("14 oct. 2025")?.time ?: 0L,
        sdf.parse("27 oct. 2025")?.time ?: 0L,
        "10 oct. 2025",
        "130$/par nuit",
        3
        )
}