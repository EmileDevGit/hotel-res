package com.leen.hotelres_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Historique_Reservation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historique_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerReservations)
        val reservations = listOf(
            Reservation("Chambre pour 2 personnes", "14 oct. 2025 - 17 oct. 2025", "10 oct. 2025", "130$/par nuit"),
            Reservation("Chambre pour 4 personnes", "8 sept. 2025 - 13 sept. 2025", "6 sept. 2025", "160$/par nuit"),
            Reservation("Chambre pour 3 personnes", "2 août 2025 - 10 août 2025", "31 juil. 2025", "145$/par nuit"),
            Reservation("Chambre pour 2 personnes", "15 juin 2025 - 21 juin 2025", "2 juin 2025", "130$/par nuit"),
            Reservation("Chambre pour 3 personnes", "10 mai 2025 - 15 mai 2025", "8 mai 2025", "150$/par nuit"),
            Reservation("Chambre pour 3 personnes", "10 juin 2025 - 13 juin 2025", "6 juin 2025", "165$/par nuit")
        )

        recyclerView.adapter = HistoriqueReservationAdapter(reservations)
    }
}