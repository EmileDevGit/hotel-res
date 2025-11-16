package com.leen.hotelres_app

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre

// Ce fichier doit être suprimmé car il n'y a qu'une seule source de données
interface ISourceDeDonnéesChambres {
    fun récupérerListeChambres(filtre: ChambreFiltre) : ArrayList<Chambre>


    @Throws(Exception::class)
    fun récupérerChambreParId(id: Int): Chambre
}