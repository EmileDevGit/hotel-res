package com.leen.hotelres_app.Entities

class Chambre(
    val numero: String ="101",
    val nombreLits: Int = 2,
    val additifs: List<String> = listOf<String>("Wifi", "Télévision", "Service à la porte"),
    val images: List<String> = listOf(
        "https://picsum.photos/800/400?image=10",
        "https://picsum.photos/800/400?image=20",
        "https://picsum.photos/800/400?image=30",
        "https://picsum.photos/800/400?image=40",
        "https://picsum.photos/800/400?image=50"
    ),
    val suite: String = "VIP",
    val prix: Float = 850F
) {

}