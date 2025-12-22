package com.leen.hotelres_app.presentation.presentateur

import com.leen.hotelres_app.R
import com.leen.hotelres_app.presentation.model.ModelHotelRes
import com.leen.hotelres_app.presentation.utils.ResourceAccessor
import org.koin.java.KoinJavaComponent.inject

class PresentateurLogin(
    private var model: ModelHotelRes = ModelHotelRes,
    private val vue: ContratFragmentPresentateurLogin.IVue
) : ContratFragmentPresentateurLogin.IPresentateur {

    private val ressourceAccessor: ResourceAccessor by inject(ResourceAccessor::class.java)

    override fun traiterConnexion() {
        val email = vue.recupererEmail()
        val motDePasse = vue.recupererMotDePasse()

        vue.afficherMessage(
            ressourceAccessor.getString( R.string.bad_credentials_error_title),
            ressourceAccessor.getString(R.string.bad_credentials_error_body)
        )
        vue.redirigerVersChambres()
    }
}