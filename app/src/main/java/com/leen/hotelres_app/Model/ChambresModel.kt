package com.leen.hotelres_app.Model

import com.leen.hotelres_app.Entities.Chambre

class ChambresModel {
    private val listeChambres: ArrayList<Chambre> = arrayListOf(
        Chambre(
            numero = "101",
            nombreLits = 1,
            prix = 450F
        ),
        Chambre(
            numero = "102",
            nombreLits = 1,
            prix = 480F
        ),
        Chambre(
            numero = "201",
            nombreLits = 2,
            prix = 650F
        ),
        Chambre(
            numero = "202",
            nombreLits = 2,
            prix = 680F
        ),
        Chambre(
            numero = "203",
            nombreLits = 2,
            prix = 720F
        ),

        Chambre(
            numero = "301",
            nombreLits = 3,
            prix = 850F
        ),
        Chambre(
            numero = "302",
            nombreLits = 3,
            prix = 890F
        ),
        Chambre(
            numero = "401",
            nombreLits = 4,
            prix = 1200F
        ),
        Chambre(
            numero = "402",
            nombreLits = 4,
            prix = 1250F
        ),

        Chambre(
            numero = "501",
            nombreLits = 2,
            prix = 750F
        ),
        Chambre(
            numero = "502",
            nombreLits = 1,
            prix = 520F
        ),
        Chambre(
            numero = "601",
            nombreLits = 3,
            prix = 920F
        ),
        Chambre(
            numero = "602",
            nombreLits = 2,
            prix = 700F
        ),

        Chambre(
            numero = "701",
            nombreLits = 2,
            prix = 950F
        ),
        Chambre(
            numero = "702",
            nombreLits = 1,
            prix = 600F
        )
    )

    fun recupérerChambres(filtre: ChambreFiltre): ArrayList<Chambre> {
        Thread.sleep(5000)
        return listeChambres.filter {
            if(filtre.prixMaximal != null){
                return@filter it.prix <= filtre.prixMaximal!!
            }else{
                return@filter true
            }

        } as ArrayList<Chambre>
    }
}


