package com.leen.hotelres_app

import com.leen.hotelres_app.Model.Reservation
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurHistoriqueReservations
import com.leen.hotelres_app.Presentateur.HistoriqueReservationsPresentateur
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class historiqueReservationsTest {
    @Mock
    lateinit var mockVue: ContratFragmentPresentateurHistoriqueReservations.IFragmentHistoriqueReservations
    lateinit var presentateur: HistoriqueReservationsPresentateur

    @Before
    fun before() {
        presentateur = HistoriqueReservationsPresentateur(mockVue)
    }

    @Test
    fun `test étant donné un présentateur lorsque l'API ne retourne aucune réservation alors la vue affiche aucuneHistorique`() {
        val videReservations = emptyList<Reservation>()

        Mockito.`when`(mockVue.afficherReservations(videReservations)).then { mockVue.aucuneHistorique() }
        mockVue.afficherReservations(videReservations)

        Mockito.verify(mockVue).aucuneHistorique()
    }

    @Test
    fun `test étant donné un présentateur lorsque le chargement échoue alors la vue affiche erreurDeAffichage`() {
        val messageErreurChargement = "Erreur lors du chargement des réservations."
        mockVue.erreurDeAffichage(messageErreurChargement)

        Mockito.verify(mockVue).erreurDeAffichage(messageErreurChargement)
    }

    @Test
    fun `test étant donné un présentateur lorsqu'il y a un historique des réservations alors la vue affiche la liste complète`() {
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

        mockVue.afficherReservations(reservations)

        Mockito.verify(mockVue).afficherReservations(reservations)
    }
}