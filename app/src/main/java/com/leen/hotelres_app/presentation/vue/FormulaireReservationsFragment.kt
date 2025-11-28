package com.leen.hotelres_app.presentation.vue

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
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
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurFormulaireReservation.IFragmentFormulaireReservations
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurFormulaireReservation.IPresentateurFormulaireReservations
import com.leen.hotelres_app.presentation.presentateur.FormulaireReservationsPresentateur
import com.leen.hotelres_app.R
import com.leen.hotelres_app.presentation.model.ModelHotelRes
import org.w3c.dom.Text
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class FormulaireReservationsFragment : Fragment(), IFragmentFormulaireReservations {

    var presentateur: IPresentateurFormulaireReservations = FormulaireReservationsPresentateur(
        this,
        ModelHotelRes)

    private lateinit var btnConfirmation : Button
    private lateinit var btnModifierDatesChoisi : Button
    private lateinit var tvDatesChoisi : TextView
    private lateinit var title: TextView
    private lateinit var btnRetour : ImageButton
    private lateinit var spinnerNbPersonnes: Spinner
    private lateinit var tvPrixSelonNbPersonnes: TextView
    private lateinit var iconeAccueil: ImageView
    private lateinit var iconeHistorique: ImageView
    private var dateDébutVar: Long = 0L
    private var dateFinVar: Long = 0L

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val vue = inflater.inflate(R.layout.fragment_formulaire_reservation, container, false)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attacherElementsVue(view)
        attacherBoutons(view)
        attacherEcouteursNavigation(view)
        Log.d("DEBUG", presentateur.toString())
        presentateur.initialiserVue()
    }

    private fun attacherBoutons(vue : View){
        Log.d("DEBUG", "Attach Button")
        iconeAccueil = vue.findViewById<ImageView>(R.id.home_icon)
        iconeHistorique = vue.findViewById<ImageView>(R.id.save_icon)
    }

    private fun attacherElementsVue(vue: View){
        Log.d("DEBUG", "Attach Element")
        btnConfirmation = vue.findViewById<Button>(R.id.btnConfirmerReservation)
        btnModifierDatesChoisi = vue.findViewById<Button>(R.id.btnModifierDates)
        tvDatesChoisi = vue.findViewById<TextView>(R.id.tvDatesChoisis)
        btnRetour = vue.findViewById<ImageButton>(R.id.btnRetour)
        spinnerNbPersonnes = vue.findViewById<Spinner>(R.id.spinnerNbPersonnes)
        tvPrixSelonNbPersonnes = vue.findViewById<TextView>(R.id.tvPrixParPersonnes)
        title = vue.findViewById<TextView>(R.id.title)
    }

    private fun attacherEcouteursNavigation(vue : View){

        iconeAccueil.setOnClickListener {
            presentateur?.naviguerVersAccueil()
            vue.findNavController().navigate(R.id.action_ecran_reservation_chambre_to_ecran_liste_chambres)
        }

        iconeHistorique.setOnClickListener {
            presentateur?.naviguerVersHistorique()
            vue.findNavController().navigate(R.id.action_ecran_reservation_chambre_to_ecran_historique_reservations)
        }

        btnConfirmation.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Confirmation")
                .setMessage(getString(R.string.reservation_confirmee))
                .setPositiveButton("OK") { _, _ ->
                    ajouterEvenementAuCalendrier(dateDébutVar, dateFinVar)
                    presentateur?.naviguerVersAccueil()
                    vue.findNavController().navigate(R.id.action_ecran_reservation_chambre_to_ecran_liste_chambres)
                }
                .show()
        }

        btnRetour.setOnClickListener {
            presentateur?.naviguerVersDetailsChambre()
            vue.findNavController().navigate(R.id.action_ecran_reservation_chambre_to_ecran_details_chambre)
        }
    }

    fun ajouterEvenementAuCalendrier(startMillis: Long, endMillis: Long) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.EVENT_TIMEZONE, "UTC")
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            putExtra(CalendarContract.Events.TITLE, "Réservation d'hôtel")
            putExtra(CalendarContract.Events.DESCRIPTION, "Votre réservation est confirmée!")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "HotelRes")
        }
        startActivity(intent)
    }

    override fun chargerInfoReservation(titre: String, dateDébut: Long, dateFin: Long, nbPersonnes: Int?) {

        title.text = titre

        dateDébutVar = dateDébut
        dateFinVar = dateFin
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
        val dateDébutString = dateFormat.format(Date(dateDébutVar))
        val dateFinString = dateFormat.format(Date(dateFinVar))

        tvDatesChoisi.text = tvDatesChoisi.context.getString(
            R.string.dates_reservationForm,
            dateDébutString,
            dateFinString
        )

        btnModifierDatesChoisi.setOnClickListener{
            val sélecteurDates = MaterialDatePicker.Builder.dateRangePicker()
                .setTheme(R.style.MaterialCalendarTheme)
                .setTitleText(getString(R.string.choissisez_vos_dates_de_reservation))
                .setSelection(Pair(dateDébutVar, dateFinVar))
                .setCalendarConstraints(null) // À implémenter au plus tard pour éviter que l'utilisateur sélectionne des dates hors de la sélection
                .build()

            sélecteurDates.show(parentFragmentManager, "TAG")

            sélecteurDates.addOnPositiveButtonClickListener {
                dateFormat.timeZone = TimeZone.getTimeZone("UTC")

                dateDébutVar = it.first
                dateFinVar = it.second

                val dateDébutString = dateFormat.format(Date(it.first))
                val dateFinString = dateFormat.format(Date(it.second))

                tvDatesChoisi.text = tvDatesChoisi.context.getString(
                    R.string.dates_reservationForm,
                    dateDébutString,
                    dateFinString
                )
            }

            sélecteurDates.addOnNegativeButtonClickListener{
                sélecteurDates.dismiss()
            }
        }

        val nbPersonnesSpinner = nbPersonnes ?: 1
        val nbPersonnesList = (nbPersonnesSpinner - 2 ..nbPersonnesSpinner + 2).toList()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nbPersonnesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNbPersonnes.adapter = adapter

        spinnerNbPersonnes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val personnesSelectionné = nbPersonnesList[position]

                if(personnesSelectionné == 4){
                    tvPrixSelonNbPersonnes.text = "Le prix augmente de 30$"
                } else if(personnesSelectionné == 5) {
                    tvPrixSelonNbPersonnes.text = "Le prix augmente de 60$"
                } else {
                    tvPrixSelonNbPersonnes.text = getString(R.string.aucun_changement_au_prix)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}