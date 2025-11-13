package com.leen.hotelres_app

import com.leen.hotelres_app.Model.Reservation
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurFormulaireReservation
import com.leen.hotelres_app.Presentateur.FormulaireReservationsPresentateur
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import java.util.Locale

@RunWith(MockitoJUnitRunner::class)
class formulaireReservationsTest {

    @Mock
    lateinit var mockVue: ContratFragmentPresentateurFormulaireReservation.IFragmentFormulaireReservations
    lateinit var presentateur: FormulaireReservationsPresentateur

    @Before
    fun before(){
        presentateur = FormulaireReservationsPresentateur(mockVue)
    }

    @Test
    fun `étant donné un présentateur, lorsqu'on arrive à la page du formulaire de réservation, alors on doit retourner les dates et le nombre de personnes à la vue`(){
        // Création d'une réservation mock
        val mockReservation = Reservation(
            "Chambre pour 2 personnes",
            111L,
            222L,
            "10 oct. 2025",
            "130$/par nuit",
            3
        )

        // Action
        Mockito.`when`(mockVue.chargerInfoReservation(mockReservation.dateDébut, mockReservation.dateFin, mockReservation.nbPersonnes)).then {  }
        mockVue.chargerInfoReservation(mockReservation.dateDébut, mockReservation.dateFin, mockReservation.nbPersonnes)

        // Vérification
        Mockito.verify(mockVue).chargerInfoReservation(mockReservation.dateDébut, mockReservation.dateFin, mockReservation.nbPersonnes)
    }
}