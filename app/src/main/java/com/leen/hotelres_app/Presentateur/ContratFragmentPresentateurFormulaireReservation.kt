package com.leen.hotelres_app.Presentateur

interface ContratFragmentPresentateurFormulaireReservation {

    interface IFragmentFormulaireReservations{
        fun chargerInfoReservation(
            dateDébut: Long,
            dateFin: Long,
            nbPersonnes : Int?
        )
    }

    interface IPresentateurFormulaireReservations{
        fun initialiserVue()
        fun naviguerVersDetailsChambre()
        fun naviguerVersAccueil()
        fun naviguerVersHistorique()
        //fun naviguerVersProfil()
    }
}