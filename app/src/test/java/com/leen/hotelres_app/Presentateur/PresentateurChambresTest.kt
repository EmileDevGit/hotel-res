package com.leen.hotelres_app.Presentateur
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre
import com.leen.hotelres_app.Model.ChambresModel
import com.leen.hotelres_app.SourceDeDonnéesFacticesChambres
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlinx.coroutines.newSingleThreadContext
import org.junit.After
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
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