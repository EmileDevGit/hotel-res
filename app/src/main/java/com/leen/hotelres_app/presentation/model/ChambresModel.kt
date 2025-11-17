package com.leen.hotelres_app.presentation.model

import com.leen.hotelres_app.services.ChambresService
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonnéesChambres
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonnéesFacticesChambres

class ChambresModel(private val source: ISourceDeDonnéesChambres = SourceDeDonnéesFacticesChambres()) {

    private val chambreService: ChambresService = ChambresService(source)

    private var chambreSelectionnée: Chambre? = null

    fun sélectionnerChambre(id: Int){
        chambreSelectionnée = chambreService.récupérerChambreparId(id)
    }

    fun recupérerChambres(filtre: ChambreFiltre): ArrayList<Chambre> {
        return chambreService.recupérerChambres(filtre)
    }
}


