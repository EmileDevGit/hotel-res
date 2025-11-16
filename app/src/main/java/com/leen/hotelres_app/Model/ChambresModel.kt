package com.leen.hotelres_app.Model

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.ISourceDeDonnéesChambres
import com.leen.hotelres_app.SourceDeDonnéesFacticesChambres

class ChambresModel(val source: ISourceDeDonnéesChambres = SourceDeDonnéesFacticesChambres()) {

    private var chambreSelectionnée: Chambre? = null

    fun sélectionnerChambre(id: Int){
            // requiert de catch l'Exception pour non trouvés.
            source.récupérerChambreParId(id)
    }

    fun recupérerChambres(filtre: ChambreFiltre): ArrayList<Chambre> {
        return source.récupérerListeChambres(filtre)
    }
}


