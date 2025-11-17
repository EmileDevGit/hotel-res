package com.leen.hotelres_app.Model

import com.leen.hotelres_app.ChambresService
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.ISourceDeDonnéesChambres
import com.leen.hotelres_app.SourceDeDonnéesFacticesChambres

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


