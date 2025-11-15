package com.leen.hotelres_app

import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurFormulaireReservation
import com.leen.hotelres_app.Presentateur.FormulaireReservationsPresentateur
import com.leen.hotelres_app.SourceDeDonnees.SourceDeDonneesBidonFormulaire
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FormulaireReservationsPresentateurTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `étant donné un présentateur, lorsqu'on arrive à la page du formulaire de réservation, alors on doit retourner les dates et le nombre de personnes à la vue`() = runTest{

        val mockVue = Mockito.mock(ContratFragmentPresentateurFormulaireReservation.IFragmentFormulaireReservations::class.java)
        val presentateur = FormulaireReservationsPresentateur(mockVue)

        val reservation = SourceDeDonneesBidonFormulaire.reservation

        // Action
        presentateur.initialiserVue()
        Thread.sleep(1000)

        // Vérification
        Mockito.verify(mockVue).chargerInfoReservation(reservation.dateDébut, reservation.dateFin, reservation.nbPersonnes)
    }
}