package com.leen.hotelres_app.presentation.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import com.leen.hotelres_app.services.ChambresService
import com.leen.hotelres_app.services.FormulaireReservationService
import com.leen.hotelres_app.services.HistoriqueReservationService
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId

object ModelHotelRes {
    private val historiqueService = HistoriqueReservationService()
    private val formulaireReservationService = FormulaireReservationService()
    private val chambreService : ChambresService by inject(ChambresService::class.java)

    private lateinit var chambreSelectionnee: Chambre
    private lateinit var reservationEnCours: Reservation

    // Usage temporaire car les dates sélectionnées ne sont pas bien enregistrées
    private var dateDebut: String = LocalDate.now().toString()
    private var dateFin: String = LocalDate.now().toString()

    fun recupererReservation(): Reservation {
        return reservationEnCours
    }

    fun recupererHistoriqueReservation(): List<Reservation>{
        return historiqueService.recupererHistoriqueReservation()
    }

    fun recupererListeChambres(filtre: ChambreFiltre): ArrayList<Chambre>{
        return chambreService.recupérerChambres(filtre)
    }

    fun selectionnerChambre(chambre: Chambre){
        this.chambreSelectionnee = chambre
    }

    fun preparerReservation(){

        val debutMillis = LocalDate.parse(dateDebut)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val finMillis = LocalDate.parse(dateFin)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val reservation = Reservation(
            chambreSelectionnee.numero,
            debutMillis,
            finMillis,
            LocalDate.now().toString(),
            chambreSelectionnee.prix,
            chambreSelectionnee.nombrePersonnes
        )
        this.reservationEnCours = reservation
    }
    fun recupererChambreSelectionnee(): Chambre{
        return chambreSelectionnee
    }

    fun enregistrerReservationEnCours(){
        formulaireReservationService.enregistrerReservation(this.reservationEnCours)
    }

    fun enregristrerFiltre(filtre: ChambreFiltre): Boolean{
        return chambreService.enregristrerFiltre(filtre)
    }

    fun recupererFiltre(): ChambreFiltre{
        return chambreService.recupererFiltre()
    }

}