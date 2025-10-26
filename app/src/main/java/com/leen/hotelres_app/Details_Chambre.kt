package com.leen.hotelres_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy

class Details_Chambre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details_chambre)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById< RecyclerView>( R.id.recyclerViewDetailsChambre )

        // Data Fixe Temporaire
        val listeUrlsPhoto = listOf(
            "https://picsum.photos/800/400?image=10",
            "https://picsum.photos/800/400?image=20",
            "https://picsum.photos/800/400?image=30",
            "https://picsum.photos/800/400?image=40",
            "https://picsum.photos/800/400?image=50"
        )

        recyclerView.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())

        CarouselSnapHelper().attachToRecyclerView( recyclerView )
        recyclerView.adapter = DetailsChambreAdapter( listeUrlsPhoto )
    }
}