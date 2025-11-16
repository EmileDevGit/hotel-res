package com.leen.hotelres_app.Presentateur

import com.leen.hotelres_app.SourceDeDonnees.SourceDeDonneesBidonHistorique
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoriqueReservationsPresentateur(private val view: ContratFragmentPresentateurHistoriqueReservations.IFragmentHistoriqueReservations) :
    ContratFragmentPresentateurHistoriqueReservations.IPresentateurHistoriqueReservations {

    override fun chargerReservations() {
        try {
            CoroutineScope(Dispatchers.IO).launch{
                Thread.sleep(1000)
                val reservations = SourceDeDonneesBidonHistorique.reservations

                CoroutineScope(Dispatchers.Main).launch {
                    if(reservations.isEmpty()){
                        view.aucuneHistorique()
                    } else {
                        view.afficherReservations(reservations)
                    }
                }
            }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch {
                view.erreurDeAffichage("Erreur lors du chargement des réservations.")
            }
        }

    }
}