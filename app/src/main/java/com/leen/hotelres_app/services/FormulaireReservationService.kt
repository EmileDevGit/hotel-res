package com.leen.hotelres_app.services

import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonnees
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidon
import com.leen.hotelres_app.presentation.model.entities.Reservation

class FormulaireReservationService(private val source: ISourceDeDonnees = SourceDeDonneesBidon()) {

    fun enregistrerReservation(reservation: Reservation){
        source.enregistrerReservation(reservation)
    }

}