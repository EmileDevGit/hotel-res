package com.leen.hotelres_app.Vue

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.datepicker.MaterialDatePicker
import com.leen.hotelres_app.Presentateur.FormulaireReservationsPresentateur
import com.leen.hotelres_app.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class FormulaireReservationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_formulaire_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var presentateur = FormulaireReservationsPresentateur()
        val btnConfirmation = view.findViewById<Button>(R.id.btnConfirmerReservation)
        val IconeAccueil: ImageView = view.findViewById(R.id.home_icon)
        val IconeHistorique: ImageView = view.findViewById(R.id.save_icon)
        val btnModifierDatesChoisi = view.findViewById<Button>(R.id.btnModifierDates)
        val tvDatesChoisi = view.findViewById<TextView>(R.id.tvDatesChoisis)
        val btnRetour = view.findViewById<ImageButton>(R.id.btnRetour)

        val reservation = presentateur.getReservation()

        IconeAccueil.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_ecran_reservation_chambre_to_ecran_liste_chambres)
        }

        IconeHistorique.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_ecran_reservation_chambre_to_ecran_historique_reservations)
        }

        btnConfirmation.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Confirmation")
                .setMessage("Réservation confirmée !")
                .setPositiveButton("OK") { _, _ ->
                    val navController = Navigation.findNavController(view)
                    navController.navigate(R.id.action_ecran_reservation_chambre_to_ecran_liste_chambres)
                }
                .show()
        }

        btnRetour.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_ecran_reservation_chambre_to_ecran_details_chambre)
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
        val dateDébutString = dateFormat.format(Date(reservation.dateDébut))
        val dateFinString = dateFormat.format(Date(reservation.dateFin))

        tvDatesChoisi.text = "Dates de réservation : $dateDébutString au $dateFinString"

        btnModifierDatesChoisi.setOnClickListener{
            val sélecteurDates = MaterialDatePicker.Builder.dateRangePicker()
                .setTheme(R.style.MaterialCalendarTheme)
                .setTitleText("Choissisez vos dates de réservation")
                .setSelection(Pair(reservation.dateDébut, reservation.dateFin))
                .setCalendarConstraints(null) // À implémenter au plus tard pour éviter que l'utilisateur sélectionne des dates hors de la sélection
                .build()

            sélecteurDates.show(parentFragmentManager, "TAG")

            sélecteurDates.addOnPositiveButtonClickListener {

                dateFormat.timeZone = TimeZone.getTimeZone("UTC")

                reservation.dateDébut = it.first
                reservation.dateFin = it.second

                val dateDébutString = dateFormat.format(Date(it.first))
                val dateFinString = dateFormat.format(Date(it.second))

                tvDatesChoisi.text = "Dates de réservation : $dateDébutString au $dateFinString"

            }

            sélecteurDates.addOnNegativeButtonClickListener{
                sélecteurDates.dismiss()
            }
        }

        val spinnerNbPersonnes = view.findViewById<Spinner>(R.id.spinnerNbPersonnes)
        val tvPrixSelonNbPersonnes = view.findViewById<TextView>(R.id.tvPrixParPersonnes)
        val nbPersonnes = listOf(1, 2, 3, 4, 5)

        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, nbPersonnes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNbPersonnes.adapter = adapter

        spinnerNbPersonnes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val personnesSelectionné = nbPersonnes[position]

                if(personnesSelectionné == 3 + 1){
                    tvPrixSelonNbPersonnes.text = "Le prix augmente de 30$"
                } else if(personnesSelectionné == 3 + 2) {
                    tvPrixSelonNbPersonnes.text = "Le prix augmente de 60$"
                } else {
                    tvPrixSelonNbPersonnes.text = "Aucun changement au prix"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}