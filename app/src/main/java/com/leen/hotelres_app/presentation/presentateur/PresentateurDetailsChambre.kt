package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.presentation.model.ModelHotelRes
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurDetailsChambre.IPresentateurDetailsChambre
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurDetailsChambre.IFragmentDetailsChambre
import com.leen.hotelres_app.presentation.vue.DetailChambreFragment
import kotlinx.coroutines.*
import java.util.Locale


class PresentateurDetailsChambre (
    var vue : IFragmentDetailsChambre = DetailChambreFragment(),
    var modele : ModelHotelRes = ModelHotelRes
): IPresentateurDetailsChambre{

    override fun initialiserVue() {
        val chambre = modele.recupererChambreSelectionnee()
        vue.chargerInfosChambre(
            chambre.numero,
            chambre.nombreLits,
            chambre.description,
            chambre.inclusions,
            chambre.nombrePersonnes,
            String.format("%.2f", chambre.prix),
            chambre.images
        )
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