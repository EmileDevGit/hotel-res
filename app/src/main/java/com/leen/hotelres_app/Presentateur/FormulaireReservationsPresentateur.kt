package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Model.FormulaireReservationModel
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurFormulaireReservation.IFragmentFormulaireReservations
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurFormulaireReservation.IPresentateurFormulaireReservations
import com.leen.hotelres_app.SourceDeDonnees.SourceDeDonneesBidonFormulaire
import com.leen.hotelres_app.Vue.FormulaireReservationsFragment
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