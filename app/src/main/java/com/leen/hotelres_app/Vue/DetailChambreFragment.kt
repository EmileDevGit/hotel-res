package com.leen.hotelres_app.Vue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.leen.hotelres_app.Model.DetailsChambreModel
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurDetailsChambre.IPresentateurDetailsChambre
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurDetailsChambre.IFragmentDetailsChambre
import com.leen.hotelres_app.Presentateur.PresentateurDetailsChambre
import com.leen.hotelres_app.R

class DetailChambreFragment : Fragment(), IFragmentDetailsChambre {

    var presentateur : IPresentateurDetailsChambre = PresentateurDetailsChambre(
        this,
        DetailsChambreModel() )
    private lateinit var titreDetailsChambre: TextView
    private lateinit var tvNbPersonne: TextView
    private lateinit var tvDescriptionDetailsChambre : TextView
    private lateinit var tvNbLitsDetailsChambre : TextView
    private lateinit var tvInclusDetailsChambre : TextView
    private lateinit var btnReserverChambre : Button
    private lateinit var iconHome : ImageView
    private lateinit var iconHistorique : ImageView
//    private lateinit var iconProfil : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_details_chambre, container, false)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attacherTextViews(view)
        attacherBoutons(view)
        attacherEcouteursNavigation(view)
        Log.d("DEBUG", presentateur.toString())
        presentateur.initialiserVue()
    }

    private fun attacherTextViews(vue : View){
        Log.d("DEBUG", "Attach Text View")
        titreDetailsChambre = vue.findViewById<TextView>(R.id.titreDetailsChambre)
        tvDescriptionDetailsChambre = vue.findViewById<TextView>(R.id.tvDescriptionDetailsChambre)
        tvInclusDetailsChambre = vue.findViewById<TextView>(R.id.tvInclusDetailsChambre)
        tvNbLitsDetailsChambre = vue.findViewById<TextView>(R.id.tvNbLitsDetailsChambre)
        tvNbPersonne = vue.findViewById<TextView>(R.id.tvNbPersonneDetailsChambre)
    }

    private fun attacherBoutons(vue : View){
        Log.d("DEBUG", "Attach Button")
        btnReserverChambre = vue.findViewById<Button>(R.id.btnReserverChambre)
        iconHome = vue.findViewById<ImageView>(R.id.home_icon)
        iconHistorique = vue.findViewById<ImageView>(R.id.save_icon)
    }

    private fun attacherEcouteursNavigation(vue: View){
        btnReserverChambre.setOnClickListener {
            presentateur?.naviguerVersReservation()
            vue.findNavController().navigate(R.id.action_ecran_details_chambre_to_ecran_reservation_chambre)
        }

        iconHome.setOnClickListener {
            presentateur?.naviguerVersAccueil()
            vue.findNavController().navigate(R.id.action_ecran_details_chambre_to_ecran_liste_chambres)
        }

        iconHistorique.setOnClickListener {
            presentateur?.naviguerVersHistorique()
            vue.findNavController().navigate(R.id.action_ecran_details_chambre_to_ecran_historique_reservations)
        }
    }

    override fun chargerInfosChambre(
        numero: String,
        nbreLits: Int,
        description: String,
        inclusions: String,
        nbrePersonnes: Int,
        prixParNuit: Float
    ) {
        Log.d("DEBUG", "SET TV TEXT")

        titreDetailsChambre.setText("Détails de la chambre ${numero}")
        tvNbPersonne.setText("Chambre pour ${nbrePersonnes} personne(s)")
        tvDescriptionDetailsChambre.setText("Description: ${description}")
        tvNbLitsDetailsChambre.setText("Nombre de lits: ${nbreLits}")
        tvInclusDetailsChambre.setText("Inclus: ${inclusions}")
        btnReserverChambre.setText("Réserver - ${prixParNuit}$ / nuit")
    }
}