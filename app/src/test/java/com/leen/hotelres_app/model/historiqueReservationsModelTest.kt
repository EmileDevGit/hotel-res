package com.leen.hotelres_app.model

import com.leen.hotelres_app.presentation.model.entities.Reservation
import junit.framework.TestCase.assertEquals
import org.junit.Test

class historiqueReservationsModelTest {

    @Test
    fun `test étant donné un model, lorsque une reservation contient des bons valeurs alors la reservation sera bien crée`() {
        val reservation = Reservation(
            titre = "Chambre pour 3 personnes",
            dateDébut = 1000L,
            dateFin = 2000L,
            dateReservation = "10 Oct 2025",
            prix = "150$",
            nbPersonnes = 3
        )

        assertEquals("Chambre pour 3 personnes", reservation.titre)
        assertEquals(1000L, reservation.dateDébut)
        assertEquals(2000L, reservation.dateFin)
        assertEquals("10 Oct 2025", reservation.dateReservation)
        assertEquals("150$", reservation.prix)
        assertEquals(3, reservation.nbPersonnes)
    }
}