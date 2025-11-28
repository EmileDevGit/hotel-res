package com.leen.hotelres_app.presentation.model.entities

import java.time.LocalDate
import java.util.Date

class ChambreFiltre(
    public var dateDebut: Date? = null,
    public var dateFin: Date? = null,
    public var prixMinimum: Float? = null,
    public var prixMaximal: Float? = null
)