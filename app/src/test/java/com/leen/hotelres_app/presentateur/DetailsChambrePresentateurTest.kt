package com.leen.hotelres_app.presentateur

import com.leen.hotelres_app.Entities.ChambreDetails
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidon
import com.leen.hotelres_app.presentation.model.DetailsChambreModel
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurDetailsChambre
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurFormulaireReservation
import com.leen.hotelres_app.presentation.presentateur.FormulaireReservationsPresentateur
import com.leen.hotelres_app.presentation.presentateur.PresentateurDetailsChambre
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import kotlin.Float

class DetailsChambrePresentateurTest {

    @Test
    fun `étant donné un PresentateurDetailsChambre, lorsqu'on charge les informations de la chambre, alors on doit retourner les dates et le nombre de personnes à la vue`(){

        val mockVue = Mockito.mock(ContratFragmentPresentateurDetailsChambre.IFragmentDetailsChambre::class.java)
        val mockModel = Mockito.mock(DetailsChambreModel::class.java)

        val chambre = ChambreDetails(
            "B260",
            2,
            125f,
            2,
            "Une chambre parfaite pour un couple avec des enfants ou des amis en vacances!",
            "Micro-onde, cafetière, mini-frigo, douche et bain séparé ainsi qu'une vue sur le paysage montagneux.",
            arrayOf(
                "https://picsum.photos/800/400?image=10",
                "https://picsum.photos/800/400?image=20",
                "https://picsum.photos/800/400?image=30",
                "https://picsum.photos/800/400?image=40",
                "https://picsum.photos/800/400?image=50"
            )
        )

        Mockito.`when`(mockModel.getChambreSelectionne()).thenReturn(chambre)

        val presentateur = PresentateurDetailsChambre(
            vue = mockVue,
            modele = mockModel)
        presentateur.initialiserVue()

        Mockito.verify(mockVue).chargerInfosChambre(
            chambre.numero,
            chambre.nbreLits,
            chambre.description,
            chambre.inclusions,
            chambre.nbrePersonnes,
            chambre.prixParNuit,
            chambre.urlsPhotos
        )
    }
}