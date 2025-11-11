package com.leen.hotelres_app.Model

import com.leen.hotelres_app.Entities.ChambreDetails

class DetailsChambreModel {
    fun getChambreSelectionne() : ChambreDetails{
        return ChambreDetails(
            "B260",
            2,
            125f,
            2,
            "Une chambre parfaite pour un couple avec des enfants ou des amis en vacances!",
            "Micro-onde, cafetière, mini-frigo, douche et bain séparé ainsi qu'une vue sur le paysage montagneux.",
            arrayOf<String>(
                "https://picsum.photos/800/400?image=10",
                "https://picsum.photos/800/400?image=20",
                "https://picsum.photos/800/400?image=30",
                "https://picsum.photos/800/400?image=40",
                "https://picsum.photos/800/400?image=50"
            )
        )
    }
}