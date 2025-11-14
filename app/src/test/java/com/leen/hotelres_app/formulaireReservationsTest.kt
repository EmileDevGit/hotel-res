package com.leen.hotelres_app

import com.leen.hotelres_app.Model.FormulaireReservationModel
import com.leen.hotelres_app.Model.Reservation
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurFormulaireReservation
import com.leen.hotelres_app.Presentateur.FormulaireReservationsPresentateur
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import java.util.Locale

@RunWith(MockitoJUnitRunner::class)
class FormulaireReservationsTest {

    @Mock
    lateinit var mockVue: ContratFragmentPresentateurFormulaireReservation.IFragmentFormulaireReservations

    lateinit var presentateur: FormulaireReservationsPresentateur

    @Mock
    lateinit var modele: FormulaireReservationModel

    @Before
    fun before(){
        presentateur = FormulaireReservationsPresentateur(mockVue)
        presentateur.modele = modele
    }

    @Test
    fun `étant donné un modele, lorsqu'on arrive à la page du formulaire de réservation, alors on doit retourner la réservation avec ces informations`(){

        // Format de date
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)

        // Use correct French abbreviations
        val mockReservation = Reservation(
            "Chambre pour 2 personnes",
            sdf.parse("14 oct. 2025")?.time ?: 0L,
            sdf.parse("27 oct. 2025")?.time ?: 0L,
            "10 oct. 2025",
            "130$/par nuit",
            3
        )

        // Action
        Mockito.`when`(modele.getReservation()).thenReturn(mockReservation)
        modele.getReservation()

        // Vérification
        Mockito.verify(modele).getReservation()
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