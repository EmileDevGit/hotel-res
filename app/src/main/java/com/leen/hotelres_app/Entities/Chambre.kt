package com.leen.hotelres_app.Entities

class Chambre(
    val numero: String ="232",
    val nombreLits: Int = 2,
    val additifs: List<String> = listOf<String>("Wifi", "Télévision", "Service à la porte"),
    val suite: String = "VIP"
) {

}