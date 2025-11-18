package com.leen.hotelres_app.model

import com.leen.hotelres_app.services.HistoriqueReservationService
import org.junit.Test

class historiqueReservationsModelTest {

    @Test
    fun `test étant donné un model, lorsque une reservation contient des bons valeurs alors la reservation sera bien crée`() {
        val serviceRes = HistoriqueReservationService()
        val historiqueResultat = serviceRes.recupererHistoriqueReservation()

        assert(historiqueResultat.isNotEmpty())
    }
}