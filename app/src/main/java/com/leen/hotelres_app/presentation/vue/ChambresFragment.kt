package com.leen.hotelres_app.presentation.vue

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurChambres
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.presentateur.PresentateurChambres
import com.leen.hotelres_app.R
import java.util.Calendar
import kotlin.String

class ChambresFragment : Fragment(), ContratFragmentPresentateurChambres.IVueChambres {

    private lateinit var chambresAdaptater: ChambresAdaptater
    private lateinit var chambresRecyclerView: RecyclerView
    private lateinit var presentateurChambres: PresentateurChambres
    private lateinit var navController: NavController
    private lateinit var btnDateDebut: Button
    private lateinit var btnDateFin: Button
    private lateinit var btnRefresh: ImageView
    private lateinit var etMontantMaximum: EditText
    private lateinit var btnVersHistoriqueDeReservation: ImageView

    private val formatDate = "%02d/%02d/%d"
    private var dateDebutCalendrier: Calendar? = null
    private var dateFinCalendrier: Calendar? = null

    private val aujourdhuiCalendrier = Calendar.getInstance()

    private lateinit var loadChambresAccueil: TextView
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chambres, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadChambresAccueil = requireActivity().findViewById(R.id.loadChambresAccueil)
        chambresRecyclerView = requireActivity().findViewById(R.id.rv_chambres)
        btnDateFin = requireActivity().findViewById(R.id.btn_date_debut)
        btnDateDebut = requireActivity().findViewById(R.id.btn_date_fin)
        btnRefresh = requireActivity().findViewById(R.id.btn_refresh_prix)
        etMontantMaximum = requireActivity().findViewById(R.id.et_prix_maximum)
        btnVersHistoriqueDeReservation = requireActivity().findViewById(R.id.vers_historique_reservation)

        chambresRecyclerView.layoutManager = LinearLayoutManager(context)

        presentateurChambres = PresentateurChambres(this)

        chambresAdaptater = ChambresAdaptater(ArrayList<Chambre>(), presentateurChambres)
        chambresRecyclerView.adapter = chambresAdaptater

        navController = Navigation.findNavController(view)

        attacherEvenements()

        showLoader()
        presentateurChambres.traiterFiltrer()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun attacherEvenements() {

        btnVersHistoriqueDeReservation.setOnClickListener {
            presentateurChambres.traiterNaviguerVersHistoriqueReservations()
        }

        btnDateDebut.setOnClickListener {
            val d = aujourdhuiCalendrier
            val picker = DatePickerDialog(
                requireContext(), R.style.DatePickerTheme,
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateDebut.text = formattedDate

                    dateDebutCalendrier = Calendar.getInstance()

                    showLoader()
                    presentateurChambres.traiterFiltrer()
                },
                aujourdhuiCalendrier.get(Calendar.YEAR),
                aujourdhuiCalendrier.get(Calendar.MONTH),
                aujourdhuiCalendrier.get(Calendar.DAY_OF_MONTH)
            )
            picker.show()
        }

        btnDateFin.setOnClickListener {
            val d = aujourdhuiCalendrier
            val picker = DatePickerDialog(
                requireContext(), R.style.DatePickerTheme,
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateFin.text = formattedDate

                    showLoader()
                    presentateurChambres.traiterFiltrer()
                },
                aujourdhuiCalendrier.get(Calendar.YEAR),
                aujourdhuiCalendrier.get(Calendar.MONTH),
                aujourdhuiCalendrier.get(Calendar.DAY_OF_MONTH)
            )
            picker.show()
        }

        etMontantMaximum.setOnFocusChangeListener { _, focus ->
            if (!focus) {
                showLoader()
                presentateurChambres.traiterFiltrer()
            }
        }

        btnRefresh.setOnClickListener {
            etMontantMaximum.text.clear()
            showLoader()
            presentateurChambres.traiterFiltrer()
        }
    }

    private fun showLoader() {
        isLoading = true
        loadChambresAccueil.visibility = View.VISIBLE
        chambresRecyclerView.visibility = View.INVISIBLE

        loadChambresAccueil.animate()
            .rotationBy(360f)
            .setDuration(700)
            .withEndAction { if (isLoading) showLoader() }
            .start()
    }

    private fun hideLoader() {
        isLoading = false
        loadChambresAccueil.clearAnimation()
        loadChambresAccueil.visibility = View.GONE
        chambresRecyclerView.visibility = View.VISIBLE
    }

    override fun afficherChambres(chambres: ArrayList<Chambre>) {
        hideLoader()
        chambresAdaptater.updateData(chambres)
    }

    override fun naviguerVersDetails(id: Int) {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_details_chambre)
    }

    override fun naviguerVersHistoriqueReservations() {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_historique_reservations)
    }

    override fun afficherNotification(texte: String) {
        Toast.makeText(context, texte, Toast.LENGTH_SHORT).show()
    }

    override fun obtenirFiltres(): ChambreFiltre {
        return ChambreFiltre(
            dateDebutCalendrier?.time,
            dateFinCalendrier?.time,
            prixMaximal = etMontantMaximum.text.toString().toFloatOrNull()
        )
    }

    override fun afficherFiltres(filtre: ChambreFiltre) {
        btnDateFin.text = ""
        btnDateDebut.text = ""
        etMontantMaximum.setText(filtre.prixMaximal?.toString() ?: "")
    }
}