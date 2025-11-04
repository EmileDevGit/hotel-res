package com.leen.hotelres_app.Vue

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doAfterTextChanged
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ChambresFragment : Fragment(), ContratVuePresentateurChambres.IVueChambres {
    private lateinit var chambresAdaptater: ChambresAdaptater
    private lateinit var chambresRecyclerView: RecyclerView
    private lateinit var presentateurChambres: PresentateurChambres
    private lateinit var navController: NavController
    private lateinit var btnDateDebut: Button
    private lateinit var btnDateFin: Button
    private lateinit var btnVersHistoriqueDeReservation: ImageView

    private val formatDate = "%02d/%02d/%d"
    private val calendrier = Calendar.getInstance()



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
        btnDateFin = requireActivity().findViewById<Button>(R.id.btn_date_debut)
        btnDateDebut = requireActivity().findViewById<Button>(R.id.btn_date_fin)
        
        btnDateDebut.setOnClickListener {
            val datePickerDialog =  DatePickerDialog(
                requireContext(),
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateDebut.text = formattedDate
                },
                calendrier.get(Calendar.YEAR),
                calendrier.get(Calendar.MONTH),
                calendrier.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
        btnDateFin.setOnClickListener {
            val datePickerDialog =  DatePickerDialog(
                requireContext(),
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateFin.text = formattedDate
                },
                calendrier.get(Calendar.YEAR),
                calendrier.get(Calendar.MONTH),
                calendrier.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        btnVersHistoriqueDeReservation = requireActivity().findViewById<ImageView>(R.id.vers_historique_reservation)
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
        btnVersHistoriqueDeReservation.setOnClickListener {
            presentateurChambres.traiterNaviguerVersHistoriqueReservations()
        }
    }


    override fun afficherChambres(chambres: ArrayList<Chambre>) {
        chambresAdaptater.updateData(chambres)
    }

    override fun réinitialiserFiltres() {
        // reinitialiser tous les champs à 0
    }

    override fun traiterNaviguerVersDetails(id: Int) {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_details_chambre)
    }

    override fun naviguerVersHistoriqueReservations() {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_historique_reservations)
    }
}