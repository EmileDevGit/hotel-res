package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Model.Reservation

interface ContratFragmentPresentateurHistoriqueReservations {

    interface IFragmentHistoriqueReservations{
        fun afficherReservations(reservations: List<Reservation>)
        fun aucuneHistorique()
    }


    interface IPresentateurHistoriqueReservations{
        fun chargerReservations()
    }
}