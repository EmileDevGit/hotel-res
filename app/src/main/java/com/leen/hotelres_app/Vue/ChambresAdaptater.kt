package com.leen.hotelres_app.Vue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.R

class ChambresAdaptater(private var chambres: ArrayList<String>) : RecyclerView.Adapter<ChambresAdaptater.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val carousselContainer : LinearLayout
        val tvTitre : TextView
        init{
            carousselContainer = view.findViewById<LinearLayout>(R.id.ll_caroussel_container) // à changer
            tvTitre = view.findViewById<TextView>(R.id.tv_chambre_description) // à changer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_chambre, parent, false)
        return ViewHolder(createdView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitre.text = "Chambre de 2 personnes"
    }

    override fun getItemCount(): Int {
        return chambres.size
    }
}