package com.leen.hotelres_app.model

import com.leen.hotelres_app.Entities.ChambreDetails
import com.leen.hotelres_app.presentation.model.DetailsChambreModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import org.mockito.Mockito

class DetailsChambreModeleTest {
    @Test
    fun `Étant donné un DetailsChambreModel, lorsqu'on appelle getChambreSelectionne, il retourne la bonne chambre`() {

        val model = DetailsChambreModel()

        val resultat = model.getChambreSelectionne()

        assertEquals("B260", resultat.numero)
        assertEquals(2, resultat.nbrePersonnes)
        assertEquals(125f, resultat.prixParNuit)
        assertEquals(2, resultat.nbreLits)
        assertEquals(
            "Une chambre parfaite pour un couple avec des enfants ou des amis en vacances!",
            resultat.description
        )
        assertEquals(
            "Micro-onde, cafetière, mini-frigo, douche et bain séparé ainsi qu'une vue sur le paysage montagneux.",
            resultat.inclusions
        )
        assertArrayEquals(
            arrayOf(
                "https://picsum.photos/800/400?image=10",
                "https://picsum.photos/800/400?image=20",
                "https://picsum.photos/800/400?image=30",
                "https://picsum.photos/800/400?image=40",
                "https://picsum.photos/800/400?image=50"
            ),
            resultat.urlsPhotos
        )
    }
}