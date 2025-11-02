package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.ContratVuePresentateurChambres
import com.leen.hotelres_app.Entities.Chambre
import java.util.Date

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

    override fun traiterFiltrer(prix: Float?, dateDebut: Date?, dateFin: Date) {
        val listeChambresFiltrés = arrayListOf(
            Chambre(),
            Chambre()
        )

        vue.afficherChambres(listeChambresFiltrés)
    }
}