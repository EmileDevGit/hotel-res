package com.leen.hotelres_app.presentation.model

import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import com.leen.hotelres_app.services.ChambresService
import com.leen.hotelres_app.services.HistoriqueReservationService
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.Locale

class ModelHotelRes {
    private val historiqueService = HistoriqueReservationService()
    private val chambreService : ChambresService by inject(ChambresService::class.java)
    fun recupererReservation(): Reservation {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)
        return Reservation(
            "Chambre pour 3 personnes",
            sdf.parse("10 nov. 2025")?.time ?: 0L,
            sdf.parse("27 nov. 2025")?.time ?: 0L,
            "10 nov. 2025",
            "155$/par nuit",
            5
        )
    }

    fun recupererHistoriqueReservation(): List<Reservation>{
        return historiqueService.recupererHistoriqueReservation()
    }

    fun recupererListeChambres(filtre: ChambreFiltre): ArrayList<Chambre>{
        return chambreService.recupérerChambres(filtre)
    }
    fun recupererChambreSelectionnee(): Chambre{
        return Chambre(
            numero = "102",
            nombreLits = 1,
            prix = 480F,
            suite = "vip",
            inclusions = "Inclus: micro-onde, frigo, bain tourbillon, espace de rangement, canapé-lit pour les invités",
            description = "Une belle chambre avec vue sur le paysage montagneux, parfait pour une escapade en famille, les animaux sont les bienvenus!",
            nombrePersonnes = 2
        )
    }

}