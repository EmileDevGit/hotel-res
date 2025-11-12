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
        val messageErreur = "Erreur lors du chargement des réservations."

        mockVue.erreurDeAffichage(messageErreur)

        Mockito.verify(mockVue).erreurDeAffichage(messageErreur)
    }

    @Test
    fun `test étant donné un présentateur lorsque des réservations existent alors la vue affiche la liste complète`() {
        val reservations = listOf(
            Reservation("Chambre 1", 0L, 0L, "1 Oct 2025", "120$/nuit", 2),
            Reservation("Chambre 2", 0L, 0L, "5 Oct 2025", "150$/nuit", 3)
        )

        mockVue.afficherReservations(reservations)

        Mockito.verify(mockVue).afficherReservations(reservations)
    }
}