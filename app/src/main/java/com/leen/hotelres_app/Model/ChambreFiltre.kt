package com.leen.hotelres_app.Model

import java.util.Date

class ChambreFiltre(
    public var dateDebut: Date? = null,
    public var dateFin: Date? = null,
    public var prix: Float? = null
) {
    public fun estFiltrable(): Boolean {
        return (dateDebut !== null || dateFin != null || prix != null)
    }
}