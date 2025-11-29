package com.leen.hotelres_app.presentation.presentateur

import android.content.Intent
import android.provider.CalendarContract
import android.view.View


interface ContratFragmentPresentateurFormulaireReservation {

    interface IFragmentFormulaireReservations{
        fun chargerInfoReservation(
            title: String,
            dateDébut: Long,
            dateFin: Long,
            nbPersonnes : Int?
        )

        fun afficherDialogue(message: String, isSaved: Boolean)
    }

    interface IPresentateurFormulaireReservations{
        fun initialiserVue()
        fun enregistrerReservation()
    }
}