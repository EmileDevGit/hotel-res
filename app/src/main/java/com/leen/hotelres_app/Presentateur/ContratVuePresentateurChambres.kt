package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre

interface ContratVuePresentateurChambres {
    interface IVueChambres {

        fun réinitialiserFiltres()

        fun afficherChambres(chambres: ArrayList<Chambre>)

        fun traiterNaviguerVersDetails(id: Int)

    }

    interface IPresentateurChambres{
        fun traiterNaviguerVersDetails(id:Int)

        fun traiterRéinitialiserFiltres()

        fun traiterFiltrer(filtre : ChambreFiltre)
    }
}