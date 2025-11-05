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

class ChambresFragment : Fragment(), ContratFragmentPresentateurChambres.IVueChambres {
    private lateinit var chambresAdaptater: ChambresAdaptater
    private lateinit var chambresRecyclerView: RecyclerView
    private lateinit var presentateurChambres: PresentateurChambres
    private lateinit var navController: NavController
    private lateinit var btnDateDebut: Button
    private lateinit var btnDateFin: Button
    private lateinit var etMontantMinimum: EditText
    private lateinit var etMontantMaximum: EditText
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chambresRecyclerView = requireActivity().findViewById<RecyclerView>(R.id.rv_chambres)
        btnDateFin = requireActivity().findViewById<Button>(R.id.btn_date_debut)
        btnDateDebut = requireActivity().findViewById<Button>(R.id.btn_date_fin)
        etMontantMinimum = requireActivity().findViewById<EditText>(R.id.et_prix_minimum)
        etMontantMaximum = requireActivity().findViewById<EditText>(R.id.et_prix_maximum)


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

                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.YEAR, selectedYear)
                        set(Calendar.MONTH, selectedMonth)
                        set(Calendar.DAY_OF_MONTH, selectedDay)
                    }

                    filtre.dateDebut = calendar.time
                    //Toast.makeText(context, "Mise à jour de la date dans le filtre", Toast.LENGTH_SHORT).show()
                },
                calendrier.get(Calendar.YEAR),
                calendrier.get(Calendar.MONTH),
                calendrier.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()

            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(context, R.color.primary))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(context, R.color.primary))

        }
        btnDateFin.setOnClickListener {
            val datePickerDialog =  DatePickerDialog(
                requireContext(), R.style.DatePickerTheme,
                {_, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = String.format(formatDate, selectedDay, selectedMonth + 1, selectedYear)
                    btnDateFin.text = formattedDate

                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.YEAR, selectedYear)
                        set(Calendar.MONTH, selectedMonth)
                        set(Calendar.DAY_OF_MONTH, selectedDay)
                    }

                    filtre.dateFin = calendar.time

                    //Toast.makeText(context, "Mise à jour de la date dans le filtre", Toast.LENGTH_SHORT).show()
                },
                calendrier.get(Calendar.YEAR),
                calendrier.get(Calendar.MONTH),
                calendrier.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()

            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(context, R.color.primary))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(context, R.color.primary))
        }

        etMontantMaximum.setOnFocusChangeListener { view, focus ->
            if(!focus){
                filtre.prixMaximal = etMontantMaximum.text.toString().toFloatOrNull()
                presentateurChambres.traiterFiltrer(filtre)
            }
        }

        etMontantMinimum.setOnFocusChangeListener { view, focus ->
            if(!focus){
                filtre.prixMinimum = etMontantMinimum.text.toString().toFloatOrNull()
                presentateurChambres.traiterFiltrer(filtre)
            }
        }
    }


    override fun afficherChambres(chambres: ArrayList<Chambre>) {
        chambresAdaptater.updateData(chambres)
    }

    override fun réinitialiserFiltres() {
        // reinitialiser tous les champs à 0 ou récupérràer de a bd
    }

    override fun naviguerVersDetails(id: Int) {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_details_chambre)
    }

    override fun naviguerVersHistoriqueReservations() {
        navController.navigate(R.id.action_ecran_liste_chambres_to_ecran_historique_reservations)
    }

    override fun notifier(texte: String) {
        Toast.makeText(context, texte, Toast.LENGTH_SHORT).show()
    }
}