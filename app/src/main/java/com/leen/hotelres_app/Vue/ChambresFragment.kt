package com.leen.hotelres_app.Vue

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.Presentateur.ContratVuePresentateurChambres
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre
import com.leen.hotelres_app.Presentateur.PresentateurChambres
import com.leen.hotelres_app.R

class ChambresFragment : Fragment(), ContratVuePresentateurChambres.IVueChambres {
    private lateinit var chambresAdaptater: ChambresAdaptater
    private lateinit var chambresRecyclerView: RecyclerView

    private lateinit var filtrePrixEditText: EditText
    private lateinit var filtreDateDebutTextView: EditText
    private lateinit var filtreDateFinTextView: EditText
    private lateinit var presentateurChambres: PresentateurChambres
    private lateinit var navController: NavController

    private val filtre: ChambreFiltre = ChambreFiltre()


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
        filtrePrixEditText = requireActivity().findViewById<EditText>(R.id.et_filtre_prix)
        filtreDateDebutTextView = requireActivity().findViewById<EditText>(R.id.et_filtre_date_debut)
        filtreDateFinTextView = requireActivity().findViewById<EditText>(R.id.et_filtre_date_fin)

        chambresRecyclerView.layoutManager = LinearLayoutManager(context)
        presentateurChambres = PresentateurChambres(this)

        chambresAdaptater = ChambresAdaptater(ArrayList<Chambre>(), presentateurChambres)
        chambresRecyclerView.adapter = chambresAdaptater

        presentateurChambres.traiterRéinitialiserFiltres()
        navController = Navigation.findNavController(view)

        attacherEvenements()
    }

    // attache les évènements à tous les composants qui en ont besoin
    private fun attacherEvenements(){
        filtrePrixEditText.doAfterTextChanged { text ->
            filtre.prix = text.toString().trim().toFloatOrNull()

            presentateurChambres.traiterFiltrer(filtre)
        }


        filtreDateDebutTextView.doAfterTextChanged { editable ->
            editable?.let {
                if (it.isNotEmpty() && !it.endsWith("/") && (it.length == 2 || it.length == 5)) {
                    it.append("/")
                }
            }
        }

        filtreDateFinTextView.doAfterTextChanged { editable ->
            editable?.let {
                if (it.isNotEmpty() && !it.endsWith("/") && (it.length == 2 || it.length == 5)) {
                    it.append("/")
                }
            }
        }
    }

    private fun formatterChaineEnDate(input:String): String{
        return input.filter { it != '/' }
    }


    override fun afficherChambres(chambres: ArrayList<Chambre>) {
    // adaptateur.update ici

        chambresAdaptater.updateData(chambres)
    }

    override fun réinitialiserFiltres() {
        filtrePrixEditText.setText("")
        filtreDateDebutTextView.setText("")
        filtreDateFinTextView.setText("")
    }

    override fun traiterNaviguerVersDetails(id: Int) {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_details_chambre)
    }
}