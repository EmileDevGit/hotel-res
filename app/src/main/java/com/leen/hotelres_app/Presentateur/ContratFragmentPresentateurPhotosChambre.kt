package com.leen.hotelres_app.Presentateur

interface ContratFragmentPresentateurPhotosChambre {
    interface IFragmentPhotosChambre{
        fun chargerPhotos(urls: Array<String>)
    }

    interface IPresentateurPhotosChambre{
        fun initialiserVue()
    }
}