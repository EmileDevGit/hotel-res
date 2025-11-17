package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.presentation.model.DetailsChambreModel
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurDetailsChambre.IPresentateurDetailsChambre
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurDetailsChambre.IFragmentDetailsChambre
import com.leen.hotelres_app.presentation.vue.DetailChambreFragment
import kotlinx.coroutines.*


class PresentateurDetailsChambre (
    var vue : IFragmentDetailsChambre = DetailChambreFragment(),
    var modele : DetailsChambreModel = DetailsChambreModel()
): IPresentateurDetailsChambre{

    override fun initialiserVue() {
        CoroutineScope(Dispatchers.IO).launch{
            val chambre = modele.getChambreSelectionne()
            CoroutineScope(Dispatchers.Main).launch {
                vue.chargerInfosChambre(
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
    }

    override fun naviguerVersReservation() {
        // Sauvegarde les données dans le modèle
    }

    override fun naviguerVersAccueil() {
        // Sauvegarde les données dans le modèle
    }

    override fun naviguerVersHistorique() {
        // Sauvegarde les données dans le modèle
    }
}