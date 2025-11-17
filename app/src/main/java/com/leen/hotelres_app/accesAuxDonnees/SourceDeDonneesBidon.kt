package com.leen.hotelres_app.accesAuxDonnees
import com.leen.hotelres_app.presentation.model.entities.Chambre
import com.leen.hotelres_app.presentation.model.entities.ChambreFiltre
import com.leen.hotelres_app.presentation.model.entities.Reservation
import java.text.SimpleDateFormat
import java.util.Locale

class SourceDeDonneesException(message : String) : Exception( message ) {}

class SourceDeDonneesBidon: ISourceDeDonnees {

    // Variables contenant les données factices
    companion object{
        private val sdf_en = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        var historiqueReservations = mutableListOf(
            Reservation(
                "Chambre pour 2 personnes",
                sdf_en.parse("14 Oct 2025")?.time ?: 0L,
                sdf_en.parse("17 Oct 2025")?.time ?: 0L,
                "10 Oct 2025",
                "130$/par nuit",
                3
            ),
            Reservation(
                "Chambre pour 4 personnes",
                sdf_en.parse("8 Sep 2025")?.time ?: 0L,
                sdf_en.parse("13 Sep 2025")?.time ?: 0L,
                "6 Sep 2025",
                "160$/par nuit",
                4
            ),
            Reservation(
                "Chambre pour 3 personnes",
                sdf_en.parse("2 Aug 2025")?.time ?: 0L,
                sdf_en.parse("10 Aug 2025")?.time ?: 0L,
                "31 Jul 2025",
                "145$/par nuit",
                3
            ),
            Reservation(
                "Chambre pour 2 personnes",
                sdf_en.parse("15 Jun 2025")?.time ?: 0L,
                sdf_en.parse("21 Jun 2025")?.time ?: 0L,
                "2 Jun 2025",
                "130$/par nuit",
                2
            ),
            Reservation(
                "Chambre pour 3 personnes",
                sdf_en.parse("10 May 2025")?.time ?: 0L,
                sdf_en.parse("15 May 2025")?.time ?: 0L,
                "8 Mai 2025",
                "150$/par nuit",
                3
            ),
            Reservation(
                "Chambre pour 3 personnes",
                sdf_en.parse("10 Jun 2025")?.time ?: 0L,
                sdf_en.parse("13 Jun 2025")?.time ?: 0L,
                "6 Jun 2025",
                "165$/par nuit",
                4
            )
        )
    }

    private val listeChambres: ArrayList<Chambre> = arrayListOf(
        Chambre(
            numero = "101",
            nombreLits = 1,
            prix = 480F,
            suite = "vip",
            inclusions = "Wifi, service personalisé",
            description = "Vue sur la mer, accès Handicapé, insonorisé",
            nombrePersonnes = 2
        ),
        Chambre(
            numero = "102",
            nombreLits = 2,
            prix = 480F,
            suite = "vip",
            inclusions = "Wifi, service à la porte",
            description = "accès Handicapé, Vue sur la mer",
            nombrePersonnes = 2
        ),
        Chambre(
            numero = "103",
            nombreLits = 2,
            prix = 650F,
            suite = "vip",
            inclusions = "",
            description = "",
            nombrePersonnes = 2
        ),
        Chambre(
            numero = "202",
            nombreLits = 2,
            prix = 680F,
            suite = "familliale",
            inclusions = "",
            description = "",
            nombrePersonnes = 2
        ),
        Chambre(
            numero = "203",
            nombreLits = 2,
            prix = 720F,
            suite = "familliale",
            inclusions = "",
            description = "",
            nombrePersonnes = 2
        ),

        Chambre(
            numero = "301",
            nombreLits = 3,
            prix = 850F,
            suite = "familliale",
            inclusions = "",
            description = "",
            nombrePersonnes = 3
        ),
        Chambre(
            numero = "302",
            nombreLits = 3,
            prix = 890F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 3
        ),
        Chambre(
            numero = "401",
            nombreLits = 4,
            prix = 1200F,
            suite = "econonomique",
            inclusions = "",
            description = "",
            nombrePersonnes = 4
        ),
        Chambre(
            numero = "402",
            nombreLits = 4,
            prix = 1250F,
            suite = "econonomique",
            inclusions = "",
            description = "",
            nombrePersonnes = 4
        ),

        Chambre(
            numero = "501",
            nombreLits = 2,
            prix = 750F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 2
        ),
        Chambre(
            numero = "502",
            nombreLits = 1,
            prix = 520F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 1
        ),
        Chambre(
            numero = "601",
            nombreLits = 3,
            prix = 920F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 3
        ),
        Chambre(
            numero = "602",
            nombreLits = 2,
            prix = 700F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 2
        ),

        Chambre(
            numero = "701",
            nombreLits = 2,
            prix = 950F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 2
        ),
        Chambre(
            numero = "702",
            nombreLits = 1,
            prix = 600F,
            suite = "",
            inclusions = "",
            description = "",
            nombrePersonnes = 1
        )
    )


    // Implémentation des méthodes de l'interface ISourceDeDonnees
    override fun getHistoriqueReservations(): List<Reservation> {
        return historiqueReservations
    }

    override fun enregistrerReservation(): Boolean{
        return true
    }

    override fun getListeChambres(): ArrayList<Chambre> {
        return listeChambres
    }

    override fun getListeChambresFiltre(filtre: ChambreFiltre): ArrayList<Chambre> {
        Thread.sleep(5000)
        return listeChambres.filter {
            if(filtre.prixMaximal != null){
                return@filter it.prix <= filtre.prixMaximal!!
            }else{
                return@filter true
            }

        } as ArrayList<Chambre>
    }

    override fun getChambreParId(id: Int): Chambre {
        if (id in 0..listeChambres.size){
            return listeChambres[id]
        }else{
            throw Exception("Chambre non trouvée")
        }
    }

}