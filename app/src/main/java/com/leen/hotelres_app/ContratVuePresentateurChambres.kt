package com.leen.hotelres_app

import com.leen.hotelres_app.Entities.Chambre
import java.util.Date

interface ContratVuePresentateurChambres {
    interface IVueChambres {

        fun réinitialiserFiltres()

        fun afficherChambres(chambres: List<Chambre>)

        fun traiterNaviguerVersDetails(id: Int)
        
    }

    interface IPresentateurChambres{
        fun traiterNaviguerVersDetails(id:Int)

        fun traiterRéinitialiserFiltres()

        fun traiterFiltrer(prix:Float?, dateDebut: Date?, dateFin: Date)
    }
}