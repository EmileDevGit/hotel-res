package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre

class PresentateurChambres(private val vue: ContratVuePresentateurChambres.IVueChambres) : ContratVuePresentateurChambres.IPresentateurChambres {

    private var listeChambres: ArrayList<Chambre>

    init {
        listeChambres = arrayListOf(
            Chambre(),
            Chambre(),
            Chambre(),
            Chambre(),
            Chambre()
        )
    }


    override fun traiterNaviguerVersDetails(id: Int) {
        vue.traiterNaviguerVersDetails(id)
    }

    override fun traiterRéinitialiserFiltres() {
        vue.réinitialiserFiltres()
        vue.afficherChambres(listeChambres)
    }

    override fun traiterFiltrer(filtre: ChambreFiltre) {
        val listeChambresFiltrés = arrayListOf(
            Chambre(),
            Chambre()
        )

        vue.afficherChambres(listeChambresFiltrés)
    }
}