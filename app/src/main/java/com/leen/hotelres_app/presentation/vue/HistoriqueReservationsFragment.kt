package com.leen.hotelres_app.presentation.vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.presentation.model.entities.Reservation
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurHistoriqueReservations
import com.leen.hotelres_app.R
import com.leen.hotelres_app.presentation.presentateur.HistoriqueReservationsPresentateur

class HistoriqueReservationsFragment : Fragment(),
    ContratFragmentPresentateurHistoriqueReservations.IFragmentHistoriqueReservations {

    private lateinit var presenter: HistoriqueReservationsPresentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_historique_reservations, container, false)

        //Icon de la page principale
        val IconAccueil: ImageView = view.findViewById(R.id.home_icon)
        IconAccueil.setOnClickListener {
            findNavController().navigate(R.id.action_ecran_historique_reservations_to_ecran_liste_chambres)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HistoriqueReservationsPresentateur(this)
        presenter.chargerReservations()
    }

    override fun afficherReservations(reservations: List<Reservation>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerReservations)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = HistoriqueReservationsAdapter(reservations)
    }

    override fun aucuneHistorique() {
        Toast.makeText(requireContext(),"Aucune historique à afficher.", Toast.LENGTH_SHORT).show()
    }

    override fun erreurDeAffichage(message: String) {}
}