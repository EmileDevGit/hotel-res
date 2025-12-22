package com.leen.hotelres_app.presentation.vue

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.leen.hotelres_app.R
import com.leen.hotelres_app.presentation.presentateur.ContratFragmentPresentateurLogin
import com.leen.hotelres_app.presentation.presentateur.PresentateurLogin
import androidx.navigation.findNavController

class LoginFragment : Fragment(), ContratFragmentPresentateurLogin.IVue {


    private lateinit var etEmail: EditText
    private lateinit var etMotDePasse: EditText
    private lateinit var btnConnexion: Button

    private lateinit var presentateur : ContratFragmentPresentateurLogin.IPresentateur
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presentateur = PresentateurLogin(vue = this)
        navController = view.findNavController()

        etEmail = requireView().findViewById<EditText>(R.id.et_email)
        etMotDePasse = requireView().findViewById<EditText>(R.id.et_password)
        btnConnexion = requireView().findViewById<Button>(R.id.btn_login)

        ajouterEvenements()
    }


    private fun ajouterEvenements(){
        btnConnexion.setOnClickListener {
            Log.d("LOGIN", "Attemps to login")
            presentateur.traiterConnexion()
        }
    }

    override fun recupererEmail(): String{
        return etEmail.text.toString()
    }

    override fun recupererMotDePasse(): String{
        return etMotDePasse.text.toString()
    }

    override fun afficherEmail(email: String) {
        val charSequence: CharSequence = email
        etEmail.setText(charSequence)
    }

    override fun afficherMotDePasse(motDePasse: String) {
        val charSequence: CharSequence = motDePasse
        etMotDePasse.setText(charSequence)
    }

    override fun redirigerVersChambres() {
        navController.navigate(R.id.action_ecran_login_to_ecran_liste_chambres)
    }

    override fun afficherMessage(titre: String, corps: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(titre)
            .setMessage(corps)
            .setPositiveButton("OK", null)
            .show()
    }

}