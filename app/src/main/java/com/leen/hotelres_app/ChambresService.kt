package com.leen.hotelres_app

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre

class ChambresService(private val source: ISourceDeDonnéesChambres = SourceDeDonnéesFacticesChambres()) {

    fun recupérerChambres(filtre: ChambreFiltre): ArrayList<Chambre> {
        return source.récupérerListeChambres(filtre)
    }

    fun récupérerChambreparId(id: Int): Chambre {
        return source.récupérerChambreParId(id)
    }

}