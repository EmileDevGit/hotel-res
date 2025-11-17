package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre

interface ContratFragmentPresentateurChambres {
    interface IVueChambres {

        fun afficherChambres(chambres: ArrayList<Chambre>)

        fun naviguerVersDetails(id: Int)
        fun naviguerVersHistoriqueReservations()

        // faire une notification
        fun afficherNotification(texte: String)

        // permet de recupérer les filtres à l'écran
        fun obtenirFiltres(): ChambreFiltre

        // permet d'afficher les filtres à l'ecran
        fun afficherFiltres(filtre: ChambreFiltre)

    }

    interface IPresentateurChambres{
        fun traiterNaviguerVersDetails(id:Int)

        fun traiterFiltrer()

        fun traiterNaviguerVersHistoriqueReservations()
    }
}