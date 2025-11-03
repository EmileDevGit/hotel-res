package com.leen.hotelres_app.Vue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.leen.hotelres_app.Model.CarouselPhotosChambreModel
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurPhotosChambre.IPresentateurPhotosChambre
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurPhotosChambre.IFragmentPhotosChambre
import com.leen.hotelres_app.Presentateur.PresentateurPhotosChambre
import com.leen.hotelres_app.R

class CarouselPhotosChambreFragment : Fragment(), IFragmentPhotosChambre {
    private lateinit var urlsPhotosChambre : Array<String>
    private lateinit var presentateur : IPresentateurPhotosChambre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentateur = PresentateurPhotosChambre(
            this,
            CarouselPhotosChambreModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue =  inflater.inflate(R.layout.fragment_child_carousel_photos_chambre, container, false)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentateur.initialiserVue()
    }

    override fun chargerPhotos(urls: Array<String>) {
        urlsPhotosChambre = urls
        view?.let{ initRecyclerView(it) }
    }

    private fun initRecyclerView(vue : View){
        val carouselPhotosRecyclerView = vue.findViewById<RecyclerView>( R.id.carouselPhotosRecyclerView )

        // Ajout du layout Carousel avec SnapHelper au recycler view
        carouselPhotosRecyclerView?.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
        CarouselSnapHelper().attachToRecyclerView( carouselPhotosRecyclerView )

        // Attache l'adapter des photos
        carouselPhotosRecyclerView?.adapter = CarouselPhotosChambreAdapter(urlsPhotosChambre)
    }
}