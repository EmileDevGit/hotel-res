package com.leen.hotelres_app.Entities

class Chambre(
    val numero: String ="232",
    val nombreLits: Int = 2,
    val additifs: List<String> = listOf<String>("Wifi", "Télévision", "Service à la porte"),
    images: List<String> = listOf(""),
    val suite: String = "VIP"
) {

}