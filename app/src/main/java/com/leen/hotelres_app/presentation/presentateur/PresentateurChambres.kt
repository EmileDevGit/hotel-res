package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.model.ModelHotelRes
import com.leen.hotelres_app.presentation.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresentateurChambres(
    private val vue: ContratFragmentPresentateurChambres.IVueChambres,
    private val model: ModelHotelRes = ModelHotelRes
) : ContratFragmentPresentateurChambres.IPresentateurChambres {

    private lateinit var chambresDisponibles: ArrayList<Chambre>


    override fun initialiserVue() {
        vue.afficherFiltres(
            model.recupererFiltre()
        )
    }

    override fun traiterNaviguerVersDetails(numero: String) {
        val chambre = chambresDisponibles.first { it.numero == numero }
        model.selectionnerChambre(chambre)
        vue.naviguerVersDetails()
    }

    override fun traiterFiltrer() {
        val filtre: ChambreFiltre = vue.obtenirFiltres()

        filtre.prix?.let {
            if(it < 0 ){
                vue.afficherNotification("Le prix maximal ne peut pas être négatif..")
                return
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            try{
                model.enregristrerFiltre(filtre)
                chambresDisponibles= model.recupererListeChambres(filtre) as ArrayList<Chambre>
                CoroutineScope(Dispatchers.Main).launch {
                    vue.afficherChambres(
                        chambresDisponibles
                    )
                }

            } catch (e: NoInternetException) {
                CoroutineScope(Dispatchers.Main).launch {
                    vue.afficherAlerte("Veuillez vérifier votre connexion Internet.")
                }
            }
        }
    }

    override fun traiterNaviguerVersHistoriqueReservations() {
        vue.naviguerVersHistoriqueReservations()
    }
}