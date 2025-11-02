package com.leen.hotelres_app.Vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.Presentateur.ContratVuePresentateurChambres
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Presentateur.PresentateurChambres
import com.leen.hotelres_app.R

class ChambresFragment : Fragment(), ContratVuePresentateurChambres.IVueChambres {
    private lateinit var chambresAdaptater: ChambresAdaptater
    private lateinit var chambresRecyclerView: RecyclerView

    private lateinit var filtrePrixTextView: TextView
    private lateinit var filtreDateDebutTextView: TextView
    private lateinit var filtreDateFinTextView: TextView
    private lateinit var presentateurChambres: PresentateurChambres
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chambres, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chambresRecyclerView = requireActivity().findViewById<RecyclerView>(R.id.rv_chambres)
        filtrePrixTextView = requireActivity().findViewById<EditText>(R.id.et_filtre_prix)
        filtreDateDebutTextView = requireActivity().findViewById<EditText>(R.id.et_filtre_date_debut)
        filtreDateFinTextView = requireActivity().findViewById<EditText>(R.id.et_filtre_date_fin)

        chambresRecyclerView.layoutManager = LinearLayoutManager(context)
        presentateurChambres = PresentateurChambres(this)

        presentateurChambres.traiterRéinitialiserFiltres()
        navController = Navigation.findNavController(view)

    }

    override fun afficherChambres(chambres: ArrayList<Chambre>) {
        chambresAdaptater = ChambresAdaptater(chambres, presentateurChambres)
        chambresRecyclerView.adapter = chambresAdaptater
    }

    override fun réinitialiserFiltres() {
        filtrePrixTextView.text = ""
        filtreDateDebutTextView.text = ""
        filtreDateFinTextView.text = ""
    }

    override fun traiterNaviguerVersDetails(id: Int) {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_details_chambre)
    }
}