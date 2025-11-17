package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.presentation.model.FormulaireReservationModel
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurFormulaireReservation.IFragmentFormulaireReservations
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurFormulaireReservation.IPresentateurFormulaireReservations
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidonFormulaire
import com.leen.hotelres_app.presentation.vue.FormulaireReservationsFragment
import kotlinx.coroutines.*

class FormulaireReservationsPresentateur(
    var vue : IFragmentFormulaireReservations = FormulaireReservationsFragment(),
    var modele : FormulaireReservationModel = FormulaireReservationModel()
) : IPresentateurFormulaireReservations {

    override fun initialiserVue() {
        CoroutineScope(Dispatchers.IO).launch{
            Thread.sleep(1000)
            val reservation = SourceDeDonneesBidonFormulaire.reservation
            CoroutineScope(Dispatchers.Main).launch {
                vue.chargerInfoReservation(
                    reservation.dateDébut,
                    reservation.dateFin,
                    reservation.nbPersonnes
                )
            }
        }
    }

    override fun naviguerVersDetailsChambre() {
        // Sauvegarde les données dans le modèle
    }

    override fun naviguerVersAccueil() {
        // Sauvegarde les données dans le modèle
    }

    override fun naviguerVersHistorique() {
        // Sauvegarde les données dans le modèle
    }
}