package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.presentation.model.entities.Reservation

interface ContratFragmentPresentateurHistoriqueReservations {

    interface IFragmentHistoriqueReservations{
        fun afficherReservations(reservations: List<Reservation>)
        fun aucuneHistorique()
        fun erreurDeAffichage(message: String)

        fun afficherLoader()
        fun cacherLoader()
    }


    interface IPresentateurHistoriqueReservations{
        fun chargerReservations()
    }
}