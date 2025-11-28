package com.leen.hotelres_app.presentation.presentateur

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.model.ModelHotelRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class PresentateurChambres(
    private val vue: ContratFragmentPresentateurChambres.IVueChambres,
    private val model: ModelHotelRes = ModelHotelRes
) : ContratFragmentPresentateurChambres.IPresentateurChambres {

    private lateinit var chambresDisponibles: ArrayList<Chambre>

    init {
        // Recupérer les données de filtres en cache ici...
        vue.afficherFiltres(
            model.recupererFiltre()
        )
    }

    override fun traiterNaviguerVersDetails(numero: String) {
        val chambre = chambresDisponibles.first { it.numero == numero }
        model.selectionnerChambre(chambre)
        vue.naviguerVersDetails()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun traiterFiltrer() {
        val filtre: ChambreFiltre = vue.obtenirFiltres()
        val aujourdhuiDate = Date()

//        if(filtre.dateDebut != null ){
//            if(filtre.dateDebut!!.before(aujourdhuiDate)){
//                vue.notifier("On ne peut pas réserver pour une date passée.")
//                return
//            }
//
//            if( filtre.dateFin != null && filtre.dateFin?.before(filtre.dateDebut) == true){
//                vue.notifier("Les date de début et de de fin d'occupations doivent etre cohérentes")
//                return
//            }
//        }


        filtre.prixMaximal?.let {
            if(it < 0 ){
                vue.afficherNotification("Le prix maximal ne peut pas être négatif..")
                return
            }
        }

        if(filtre.prixMinimum != null){

        }

        CoroutineScope(Dispatchers.IO).launch {
            model.enregristrerFiltre(filtre)
            chambresDisponibles= model.recupererListeChambres(filtre)
            CoroutineScope(Dispatchers.Main).launch {
                vue.afficherChambres(
                    chambresDisponibles
                )
            }
        }
    }

    override fun traiterNaviguerVersHistoriqueReservations() {
        vue.naviguerVersHistoriqueReservations()
    }
}