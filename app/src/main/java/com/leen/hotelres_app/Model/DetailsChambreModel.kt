package com.leen.hotelres_app.Model

class DetailsChambreModel {
    fun getChambreSelectionne() : Chambre{
        return Chambre(
            "B260",
            2,
            125f,
            2,
            "Une chambre parfaite pour un couple avec des enfants ou des amis en vacances!",
            "Micro-onde, cafetière, mini-frigo, douche et bain séparé ainsi qu'une vue sur le paysage montagneux."
        )
    }
}