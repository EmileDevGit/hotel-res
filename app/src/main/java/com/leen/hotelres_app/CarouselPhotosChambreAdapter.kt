package com.leen.hotelres_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarouselPhotosChambreAdapter(private val listeUrlPhotos: List<String>) :

    RecyclerView.Adapter<CarouselPhotosChambreAdapter.PhotoChambreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoChambreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_detail_chambre, parent, false)
        return PhotoChambreViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoChambreViewHolder, position: Int) {
        val photoUrl = listeUrlPhotos[position]
        //Log.d("Adapter", "Item ${position}: ${photoUrl}")

        Glide.with( holder.photoHolder.context )
            .load( photoUrl )
            // à implémenter plus tard?
            //.placeholder(R.drawable.placeholder)
            //.error(R.drawable.error_image)
            .into( holder.photoHolder )
    }

    override fun getItemCount(): Int {
        return listeUrlPhotos.size
    }
    inner class PhotoChambreViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView ) {
        val photoHolder: ImageView = itemView.findViewById( R.id.carouselImage )

    }
}


