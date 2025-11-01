package com.leen.hotelres_app.Model

data class Reservation(
    val titre: String,
    val dateDébut: Long,
    val dateFin: Long,
    val dateReservation: String,
    val prix: String,
    val nbPersonnes: Int?
)