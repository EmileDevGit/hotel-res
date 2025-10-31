package com.leen.hotelres_app.Vue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.R
import com.leen.hotelres_app.Entities.Reservation

class HistoriqueReservationsAdapter(
    private val reservations: List<Reservation>
) : RecyclerView.Adapter<HistoriqueReservationsAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitre: TextView = itemView.findViewById(R.id.tvTitre)
        val tvDates: TextView = itemView.findViewById(R.id.tvDates)
        val tvDateReservation: TextView = itemView.findViewById(R.id.tvDateReservation)
        val tvPrix: TextView = itemView.findViewById(R.id.tvPrix)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historique, parent, false)
        return ReservationViewHolder(view)
    }

    override fun getItemCount(): Int = reservations.size

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.tvTitre.text = reservation.titre
        holder.tvDates.text = reservation.dates
        holder.tvDateReservation.text = reservation.dateReservation
        holder.tvPrix.text = reservation.prix
    }
}