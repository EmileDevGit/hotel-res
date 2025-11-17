package com.leen.hotelres_app.presentation.model

data class Reservation(
    val titre: String,
    var dateDébut: Long,
    var dateFin: Long,
    val dateReservation: String,
    val prix: String,
    val nbPersonnes : Int?
)