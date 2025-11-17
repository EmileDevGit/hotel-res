package com.leen.hotelres_app.services

import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonnees
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidon

class FormulaireReservationService(private val source: ISourceDeDonnees = SourceDeDonneesBidon()) {

    fun enregistrerReservation(): Boolean{
        return source.enregistrerReservation()
    }

}