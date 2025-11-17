package com.leen.hotelres_app.Presentateur

import android.os.Build
import androidx.annotation.RequiresApi
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre
import com.leen.hotelres_app.Model.ChambresModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class PresentateurChambres(private val vue: ContratFragmentPresentateurChambres.IVueChambres, private val model: ChambresModel = ChambresModel()) : ContratFragmentPresentateurChambres.IPresentateurChambres {


    init {
        // Recupérer les données de filtres en cache ici...
        vue.afficherFiltres(
            ChambreFiltre()
        )
    }

    override fun traiterNaviguerVersDetails(id: Int) {
        vue.naviguerVersDetails(id)
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

        vue.afficherNotification("Chargement... Veuillez patienter")
        CoroutineScope(Dispatchers.IO).launch {
            val résultat: ArrayList<Chambre> = model.recupérerChambres(filtre)

            CoroutineScope(Dispatchers.Main).launch {
                vue.afficherChambres(
                    résultat
                )
            }
        }

    }

    override fun traiterNaviguerVersHistoriqueReservations() {
        vue.naviguerVersHistoriqueReservations()
    }
}