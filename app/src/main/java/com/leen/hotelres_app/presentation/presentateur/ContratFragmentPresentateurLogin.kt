package com.leen.hotelres_app.presentation.presentateur

interface ContratFragmentPresentateurLogin {
    public interface IVue {
        fun recupererEmail(): String
        fun recupererMotDePasse(): String
        fun afficherEmail(email: String)
        fun afficherMotDePasse(motDePasse: String)
        fun redirigerVersChambres()
        fun afficherMessage(titre: String, corps: String)
    }

    public interface IPresentateur {
        fun traiterConnexion()
    }
}