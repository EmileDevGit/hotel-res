package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Model.Reservation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoriqueReservationsPresentateur(private val view: ContratFragmentPresentateurHistoriqueReservations.IFragmentHistoriqueReservations) :
    ContratFragmentPresentateurHistoriqueReservations.IPresentateurHistoriqueReservations {

    override fun chargerReservations() {
        try {
            CoroutineScope(Dispatchers.IO).launch{
                val sdf = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.ENGLISH)

                val reservations = listOf(
                    Reservation(
                        "Chambre pour 2 personnes",
                        sdf.parse("14 Oct 2025")?.time ?: 0L,
                        sdf.parse("17 Oct 2025")?.time ?: 0L,
                        "10 Oct 2025",
                        "130$/par nuit",
                        3
                    ),
                    Reservation(
                        "Chambre pour 4 personnes",
                        sdf.parse("8 Sep 2025")?.time ?: 0L,
                        sdf.parse("13 Sep 2025")?.time ?: 0L,
                        "6 Sep 2025",
                        "160$/par nuit",
                        4
                    ),
                    Reservation(
                        "Chambre pour 3 personnes",
                        sdf.parse("2 Aug 2025")?.time ?: 0L,
                        sdf.parse("10 Aug 2025")?.time ?: 0L,
                        "31 Jul 2025",
                        "145$/par nuit",
                        3
                    ),
                    Reservation(
                        "Chambre pour 2 personnes",
                        sdf.parse("15 Jun 2025")?.time ?: 0L,
                        sdf.parse("21 Jun 2025")?.time ?: 0L,
                        "2 Jun 2025",
                        "130$/par nuit",
                        2
                    ),
                    Reservation(
                        "Chambre pour 3 personnes",
                        sdf.parse("10 May 2025")?.time ?: 0L,
                        sdf.parse("15 May 2025")?.time ?: 0L,
                        "8 Mai 2025",
                        "150$/par nuit",
                        3
                    ),
                    Reservation(
                        "Chambre pour 3 personnes",
                        sdf.parse("10 Jun 2025")?.time ?: 0L,
                        sdf.parse("13 Jun 2025")?.time ?: 0L,
                        "6 Jun 2025",
                        "165$/par nuit",
                        4
                    )
                )

                CoroutineScope(Dispatchers.Main).launch {
                    view.afficherReservations(reservations)
                }
            }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch {
                view.erreurDeAffichage("Erreur lors du chargement des réservations.")
            }
        }

    }
}