package com.leen.hotelres_app.accesAuxDonnees
import com.leen.hotelres_app.presentation.model.Reservation
import java.text.SimpleDateFormat
import java.util.Locale

class SourceDeDonneesException(message : String) : Exception( message ) {}

object SourceDeDonneesBidon {

    val sdf_fr = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)
    val sdf_en = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    var reservation = Reservation(
        "Chambre pour 2 personnes",
        sdf_fr.parse("14 oct. 2025")?.time ?: 0L,
        sdf_fr.parse("27 oct. 2025")?.time ?: 0L,
        "10 oct. 2025",
        "130$/par nuit",
        3
    )

    var historiqueReservations = mutableListOf(
        Reservation(
            "Chambre pour 2 personnes",
            sdf_en.parse("14 Oct 2025")?.time ?: 0L,
            sdf_en.parse("17 Oct 2025")?.time ?: 0L,
            "10 Oct 2025",
            "130$/par nuit",
            3
        ),
        Reservation(
            "Chambre pour 4 personnes",
            sdf_en.parse("8 Sep 2025")?.time ?: 0L,
            sdf_en.parse("13 Sep 2025")?.time ?: 0L,
            "6 Sep 2025",
            "160$/par nuit",
            4
        ),
        Reservation(
            "Chambre pour 3 personnes",
            sdf_en.parse("2 Aug 2025")?.time ?: 0L,
            sdf_en.parse("10 Aug 2025")?.time ?: 0L,
            "31 Jul 2025",
            "145$/par nuit",
            3
        ),
        Reservation(
            "Chambre pour 2 personnes",
            sdf_en.parse("15 Jun 2025")?.time ?: 0L,
            sdf_en.parse("21 Jun 2025")?.time ?: 0L,
            "2 Jun 2025",
            "130$/par nuit",
            2
        ),
        Reservation(
            "Chambre pour 3 personnes",
            sdf_en.parse("10 May 2025")?.time ?: 0L,
            sdf_en.parse("15 May 2025")?.time ?: 0L,
            "8 Mai 2025",
            "150$/par nuit",
            3
        ),
        Reservation(
            "Chambre pour 3 personnes",
            sdf_en.parse("10 Jun 2025")?.time ?: 0L,
            sdf_en.parse("13 Jun 2025")?.time ?: 0L,
            "6 Jun 2025",
            "165$/par nuit",
            4
        )
    )

}