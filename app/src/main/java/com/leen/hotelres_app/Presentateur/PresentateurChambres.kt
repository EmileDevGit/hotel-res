package com.leen.hotelres_app.Presentateur

import android.os.Build
import androidx.annotation.RequiresApi
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre
import java.util.Date

class PresentateurChambres(private val vue: ContratFragmentPresentateurChambres.IVueChambres) : ContratFragmentPresentateurChambres.IPresentateurChambres {

    private var listeChambres: ArrayList<Chambre>

    init {
        listeChambres = arrayListOf(
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
            Chambre(
                numero = "201",
                nombreLits = 2,
                prix = 650F
            ),
            Chambre(
                numero = "202",
                nombreLits = 2,
                prix = 680F
            ),
            Chambre(
                numero = "203",
                nombreLits = 2,
                prix = 720F
            ),

            Chambre(
                numero = "301",
                nombreLits = 3,
                prix = 850F
            ),
            Chambre(
                numero = "302",
                nombreLits = 3,
                prix = 890F
            ),
            Chambre(
                numero = "401",
                nombreLits = 4,
                prix = 1200F
            ),
            Chambre(
                numero = "402",
                nombreLits = 4,
                prix = 1250F
            ),

            Chambre(
                numero = "501",
                nombreLits = 2,
                prix = 750F
            ),
            Chambre(
                numero = "502",
                nombreLits = 1,
                prix = 520F
            ),
            Chambre(
                numero = "601",
                nombreLits = 3,
                prix = 920F
            ),
            Chambre(
                numero = "602",
                nombreLits = 2,
                prix = 700F
            ),

            Chambre(
                numero = "701",
                nombreLits = 2,
                prix = 950F
            ),
            Chambre(
                numero = "702",
                nombreLits = 1,
                prix = 600F
            )
        )
    }


    override fun traiterNaviguerVersDetails(id: Int) {
        vue.naviguerVersDetails(id)
    }

    override fun traiterRéinitialiserFiltres() {
        vue.réinitialiserFiltres()
        vue.afficherChambres(listeChambres)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun traiterFiltrer(filtre: ChambreFiltre) {
        val aujourdhuiDate = Date()
        var listeChambresFiltres = listeChambres

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

        filtre.prixMinimum?.let {
            if(it < 0 ){

                vue.notifier("Le prix minimum ne peut pas être négatif..")
                return
            }
        }

        filtre.prixMaximal?.let {
            if(it < 0 ){
                vue.notifier("Le prix maximal ne peut pas être négatif..")
                return
            }
        }

        if(filtre.prixMaximal != null && filtre.prixMinimum != null){
            listeChambresFiltres = arrayListOf(
                Chambre()
            )
        }


        vue.afficherChambres(listeChambresFiltres)
    }

    override fun traiterNaviguerVersHistoriqueReservations() {
        vue.naviguerVersHistoriqueReservations()
    }
}