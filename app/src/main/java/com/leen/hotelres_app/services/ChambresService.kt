package com.leen.hotelres_app.services

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonnéesChambres
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonnéesFacticesChambres
import com.leen.hotelres_app.presentation.model.ChambreFiltre

class ChambresService(private val source: ISourceDeDonnéesChambres = SourceDeDonnéesFacticesChambres()) {

    fun recupérerChambres(filtre: ChambreFiltre): ArrayList<Chambre> {
        return source.récupérerListeChambres(filtre)
    }

    fun récupérerChambreparId(id: Int): Chambre {
        return source.récupérerChambreParId(id)
    }

}