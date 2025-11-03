package com.leen.hotelres_app.Model

data class Reservation(
    val titre: String,
    var dateDébut: Long,
    var dateFin: Long,
    val dateReservation: String,
    val prix: String,
    val nbPersonnes : Int?
)