package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.presentation.model.Reservation

interface ContratFragmentPresentateurHistoriqueReservations {

    interface IFragmentHistoriqueReservations{
        fun afficherReservations(reservations: List<Reservation>)
        fun aucuneHistorique()
        fun erreurDeAffichage(message: String)
    }


    interface IPresentateurHistoriqueReservations{
        fun chargerReservations()
    }
}