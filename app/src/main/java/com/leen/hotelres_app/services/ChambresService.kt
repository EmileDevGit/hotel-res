package com.leen.hotelres_app.services

import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonnees
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidon
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre

class ChambresService(private val source: ISourceDeDonnees = SourceDeDonneesBidon()) {

    fun recupérerChambres(filtre: ChambreFiltre): ArrayList<Chambre> {
        return source.getListeChambresFiltre(filtre)
    }

    fun récupérerChambreparId(id: Int): Chambre {
        return source.getChambreParId(id)
    }

    fun enregristrerFiltre(filtre: ChambreFiltre): Boolean {
        return source.enregistrerFiltres(filtre)
    }

    fun recupererFiltre() : ChambreFiltre {
        return source.getFiltres()
    }
}