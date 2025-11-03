package com.leen.hotelres_app.Vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.leen.hotelres_app.R

class CarouselPhotosChambreFragment : Fragment() {
    private lateinit var urlsPhotosChambre : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        urlsPhotosChambre = listOf(
            "https://picsum.photos/800/400?image=10",
            "https://picsum.photos/800/400?image=20",
            "https://picsum.photos/800/400?image=30",
            "https://picsum.photos/800/400?image=40",
            "https://picsum.photos/800/400?image=50"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_carousel_photos_chambre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carouselPhotosRecyclerView = view.findViewById<RecyclerView>(R.id.carouselPhotosRecyclerView)

        carouselPhotosRecyclerView?.apply {
            layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())

            // Vérifier si un SnapHelper n'est pas déjà attaché
            if (onFlingListener == null) {
                CarouselSnapHelper().attachToRecyclerView(this)
            }

            adapter = CarouselPhotosChambreAdapter(urlsPhotosChambre)
        }

    }
}