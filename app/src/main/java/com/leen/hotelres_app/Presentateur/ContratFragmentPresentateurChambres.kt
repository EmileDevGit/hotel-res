package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre

interface ContratFragmentPresentateurChambres {
    interface IVueChambres {

        fun réinitialiserFiltres()

        fun afficherChambres(chambres: ArrayList<Chambre>)

        fun naviguerVersDetails(id: Int)
        fun naviguerVersHistoriqueReservations()

        // faire une notification
        fun notifier(texte: String)
    }

    interface IPresentateurChambres{
        fun traiterNaviguerVersDetails(id:Int)

        fun traiterRéinitialiserFiltres()

        fun traiterFiltrer(filtre : ChambreFiltre)

        fun traiterNaviguerVersHistoriqueReservations()
    }
}