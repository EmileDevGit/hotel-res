package com.leen.hotelres_app.Model

class CarouselPhotosChambreModel {
    private lateinit var urlsPhotos : Array<String>

    fun getUrlsPhoto() : Array<String> {

        // Simulation des données
        urlsPhotos = arrayOf<String>(
            "https://picsum.photos/800/400?image=10",
            "https://picsum.photos/800/400?image=20",
            "https://picsum.photos/800/400?image=30",
            "https://picsum.photos/800/400?image=40",
            "https://picsum.photos/800/400?image=50"
        )
        return urlsPhotos
    }
}