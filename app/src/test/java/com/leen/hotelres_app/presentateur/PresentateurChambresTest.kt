package com.leen.hotelres_app.presentateur
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurChambres
import com.leen.hotelres_app.presentation.presentateur.PresentateurChambres
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlinx.coroutines.newSingleThreadContext
import org.junit.After
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.Calendar

@RunWith(MockitoJUnitRunner::class)
class PresentateurChambresTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

//    @Test
//    fun `Étant donné un présentateurChambres, lorsqu'on applique un filtre, on obtient le resultat`(){
//
//    }

    @Test fun `Étant donné un présentateurChambre, lorsqu'on essaie d'appliquer des filtres, il récupère les filtres et fait et vas chercher les données des chambres` () {


        val sampleChambreFiltre = ChambreFiltre(
            Calendar.getInstance().time,
            null,
            prixMaximal = 500F
        )

        val sampleArrayOfChambres = arrayListOf<Chambre>(
            Chambre(
                numero = "101",
                nombreLits = 1,
                prix = 450F
            ),
            Chambre(
                numero = "102",
                nombreLits = 1,
                prix = 480F
            ),
        )

        val mockVue = Mockito.mock(ContratFragmentPresentateurChambres.IVueChambres::class.java)
        Mockito.`when`(mockVue.obtenirFiltres())
                .thenReturn(sampleChambreFiltre)

        val mockModel = Mockito.mock(ChambresModel::class.java)
        Mockito.`when`(mockModel.recupérerChambres(sampleChambreFiltre) )
            .thenReturn(sampleArrayOfChambres)


        val cobaye = PresentateurChambres(
            vue = mockVue,
            model = mockModel
        )

        cobaye.traiterFiltrer()


        Mockito.verify(mockVue).obtenirFiltres()
        Mockito.verify(mockModel).recupérerChambres(sampleChambreFiltre)

    }
}