package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Entities.ChambreDetails
import com.leen.hotelres_app.Model.DetailsChambreModel
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurDetailsChambre.IPresentateurDetailsChambre
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurDetailsChambre.IFragmentDetailsChambre
import com.leen.hotelres_app.Vue.DetailChambreFragment
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