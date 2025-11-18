package com.leen.hotelres_app.di

import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonnees
import com.leen.hotelres_app.accesAuxDonnees.ISourceDeDonneesFichier
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesBidon
import com.leen.hotelres_app.accesAuxDonnees.SourceDeDonneesFichier
import com.leen.hotelres_app.services.ChambresService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

// permet de créer les objects préconçus que kotlin vas utiliser comme "sujet d'injection"
val appModule = module {

    single<ISourceDeDonnees> {
        SourceDeDonneesBidon()
    }

    single<ISourceDeDonneesFichier> {
        SourceDeDonneesFichier(
            context = androidContext()
        )
    }


    single {
        ChambresService(get(), get())
    }
}