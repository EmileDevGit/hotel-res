package com.leen.hotelres_app.presentation.vue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.R
import com.leen.hotelres_app.presentation.model.entities.Reservation
import java.text.SimpleDateFormat
import java.util.Locale

class HistoriqueReservationsAdapter(
    private val reservations: List<Reservation>
) : RecyclerView.Adapter<HistoriqueReservationsAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitre: TextView = itemView.findViewById(R.id.tvTitre)
        val tvDates: TextView = itemView.findViewById(R.id.tvDates)
        val tvDateReservation: TextView = itemView.findViewById(R.id.tvDateReservation)
        val tvPrix: TextView = itemView.findViewById(R.id.tvPrix)
        val tvNbrPersonnes: TextView = itemView.findViewById(R.id.tvNbrPersonnes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historique, parent, false)
        return ReservationViewHolder(view)
    }

    override fun getItemCount(): Int = reservations.size

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]

        val sdfDisplay = SimpleDateFormat("dd MMM yyyy", Locale.FRANCE)
        val dateRange = "${sdfDisplay.format(reservation.dateDébut)} - ${sdfDisplay.format(reservation.dateFin)}"

        holder.tvTitre.text = reservation.titre
        holder.tvDates.text = dateRange
        holder.tvNbrPersonnes.text = "Nombre de personnes: ${reservation.nbPersonnes}"
        holder.tvDateReservation.text = "Date de réservation: ${reservation.dateReservation}"
        holder.tvPrix.text = "Prix: ${reservation.prix}"
    }
}