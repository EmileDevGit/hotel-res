package com.leen.hotelres_app.Presentateur
import android.util.Log
import com.leen.hotelres_app.Model.CarouselPhotosChambreModel
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurPhotosChambre.IPresentateurPhotosChambre
import com.leen.hotelres_app.Presentateur.ContratFragmentPresentateurPhotosChambre.IFragmentPhotosChambre
import com.leen.hotelres_app.Vue.CarouselPhotosChambreFragment

class PresentateurPhotosChambre (
    var vue : IFragmentPhotosChambre = CarouselPhotosChambreFragment(),
    var modele : CarouselPhotosChambreModel = CarouselPhotosChambreModel()
): IPresentateurPhotosChambre {

    override fun initialiserVue() {
        val urls = modele.getUrlsPhoto()
        vue.chargerPhotos(urls)
    }

}