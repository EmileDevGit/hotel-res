package com.leen.hotelres_app.model

import com.leen.hotelres_app.presentation.model.entities.Reservation
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidon
import com.leen.hotelres_app.presentation.model.ModelHotelRes
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class FormulaireReservationsModeleTest {

    @Test
    fun `Étant donné un modèle, lorsqu’une réservation est créée avec des valeurs valides, elle est créée avec succès`(){

        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)

        val reservation = Reservation(
            "Chambre pour 3 personnes",
            sdf.parse("10 nov. 2025")?.time ?: 0L,
            sdf.parse("27 nov. 2025")?.time ?: 0L,
            "10 nov. 2025",
            "155$/par nuit",
            5
        )

        assertEquals("Chambre pour 3 personnes", reservation.titre)
        assertEquals(sdf.parse("10 nov. 2025")?.time ?: 0L, reservation.dateDébut)
        assertEquals(sdf.parse("27 nov. 2025")?.time ?: 0L, reservation.dateFin)
        assertEquals("10 nov. 2025", reservation.dateReservation)
        assertEquals("155$/par nuit", reservation.prix)
        assertEquals(5, reservation.nbPersonnes)
    }
}