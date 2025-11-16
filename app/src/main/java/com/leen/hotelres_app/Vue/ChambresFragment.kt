package com.leen.hotelres_app.Vue

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurChambres
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.Model.ChambreFiltre
import com.leen.hotelres_app.Presentateur.PresentateurChambres
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

    private var dateDebutCalendrier : Calendar? = null
    private var dateFinCalendrier : Calendar? = null

    private val aujourdhuiCalendrier = Calendar.getInstance()


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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chambresRecyclerView = requireActivity().findViewById<RecyclerView>(R.id.rv_chambres)
        btnDateFin = requireActivity().findViewById<Button>(R.id.btn_date_debut)
        btnDateDebut = requireActivity().findViewById<Button>(R.id.btn_date_fin)
        btnRefresh = requireActivity().findViewById<ImageView>(R.id.btn_refresh_prix)
        //etMontantMinimum = requireActivity().findViewById<EditText>(R.id.et_prix_minimum)
        etMontantMaximum = requireActivity().findViewById<EditText>(R.id.et_prix_maximum)


        btnVersHistoriqueDeReservation = requireActivity().findViewById<ImageView>(R.id.vers_historique_reservation)
        chambresRecyclerView.layoutManager = LinearLayoutManager(context)
        presentateurChambres = PresentateurChambres(this)

        chambresAdaptater = ChambresAdaptater(ArrayList<Chambre>(), presentateurChambres)
        chambresRecyclerView.adapter = chambresAdaptater

        presentateurChambres.traiterFiltrer()
        navController = Navigation.findNavController(view)

        attacherEvenements()
    }

    // attache les évènements à tous les composants qui en ont besoin
    @RequiresApi(Build.VERSION_CODES.O)
    private fun attacherEvenements(){
        btnVersHistoriqueDeReservation.setOnClickListener {
            presentateurChambres.traiterNaviguerVersHistoriqueReservations()
        }

        btnDateDebut.setOnClickListener {
            val datePickerDialog =  DatePickerDialog(
                requireContext(), R.style.DatePickerTheme,
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateDebut.text = formattedDate

                    dateDebutCalendrier = Calendar.getInstance()

                    dateDebutCalendrier?.let {
                        it.apply {
                            set(Calendar.YEAR, selectedYear)
                            set(Calendar.MONTH, selectedMonth)
                            set(Calendar.DAY_OF_MONTH, selectedDay)
                        }
                    }

                    presentateurChambres.traiterFiltrer()
                },
                aujourdhuiCalendrier.get(Calendar.YEAR),
                aujourdhuiCalendrier.get(Calendar.MONTH),
                aujourdhuiCalendrier.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
        btnDateFin.setOnClickListener {
            val datePickerDialog =  DatePickerDialog(
                requireContext(), R.style.DatePickerTheme,
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateFin.text = formattedDate

                    dateFinCalendrier?.let{
                        it.apply {
                            set(Calendar.YEAR, selectedYear)
                            set(Calendar.MONTH, selectedMonth)
                            set(Calendar.DAY_OF_MONTH, selectedDay)
                        }
                    }

                    presentateurChambres.traiterFiltrer()
                },
                aujourdhuiCalendrier.get(Calendar.YEAR),
                aujourdhuiCalendrier.get(Calendar.MONTH),
                aujourdhuiCalendrier.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        etMontantMaximum.setOnFocusChangeListener { view, focus ->
            if(!focus){
                presentateurChambres.traiterFiltrer()
            }
        }

        btnRefresh.setOnClickListener {
            etMontantMaximum.text.clear()
            presentateurChambres.traiterFiltrer()
        }
    }

    override fun afficherChambres(chambres: ArrayList<Chambre>) {
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
            dateFinCalendrier?.time ,
            prixMaximal = etMontantMaximum.text.toString().toFloatOrNull()
        )
    }

    override fun afficherFiltres(filtre: ChambreFiltre) {
        etMontantMaximum.setText(
            filtre.prixMaximal.toString() as CharSequence
        )

        btnDateDebut.text = filtre.dateDebut.toString().format(formatDate)
        dateDebutCalendrier?.time = filtre.dateDebut ?: aujourdhuiCalendrier.time

        btnDateFin.text = filtre.dateFin.toString().format(formatDate)
        dateFinCalendrier?.time = filtre.dateFin ?: aujourdhuiCalendrier.time
    }
}