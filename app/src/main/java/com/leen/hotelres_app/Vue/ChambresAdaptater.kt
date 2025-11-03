package com.leen.hotelres_app.Vue

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.leen.hotelres_app.Presentateur.ContratVuePresentateurChambres
import com.leen.hotelres_app.Entities.Chambre
import com.leen.hotelres_app.R

class ChambresAdaptater(private var chambres: ArrayList<Chambre>, private val presentateurChambre: ContratVuePresentateurChambres.IPresentateurChambres) : RecyclerView.Adapter<ChambresAdaptater.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitre : TextView
        val btnDetails: Button
        val carouselPhotosRecyclerView: RecyclerView
        init{
            tvTitre = view.findViewById<TextView>(R.id.tv_chambre_description)
            btnDetails = view.findViewById<Button>(R.id.btn_details)
            carouselPhotosRecyclerView = view.findViewById<RecyclerView>(R.id.carouselPhotosRecyclerView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_chambre, parent, false)
        return ViewHolder(createdView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val chambre = chambres[position]
        holder.tvTitre.text = "Chambre de ${chambre.nombreLits} lits"

        holder.carouselPhotosRecyclerView.layoutManager =
            CarouselLayoutManager(FullScreenCarouselStrategy())
        CarouselSnapHelper().attachToRecyclerView( holder.carouselPhotosRecyclerView )
        holder.carouselPhotosRecyclerView?.adapter = CarouselPhotosChambreAdapter(chambre.images)

        holder.btnDetails.setOnClickListener {
            presentateurChambre.traiterNaviguerVersDetails(position)
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: ArrayList<Chambre>) {
        chambres = newData
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return chambres.size
    }
}