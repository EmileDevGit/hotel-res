package com.leen.hotelres_app.presentation.vue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.leen.hotelres_app.R

class LoginFragment : Fragment() {


    private lateinit var etEmail: EditText
    private lateinit var etMotDePasse: EditText
    private lateinit var btnConnexion: Button

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

        etEmail = requireView().findViewById<EditText>(R.id.et_email)
        etMotDePasse = requireView().findViewById<EditText>(R.id.et_password)
        btnConnexion = requireView().findViewById<Button>(R.id.btn_login)

        ajouterEvenements()
    }


    private fun ajouterEvenements(){
        btnConnexion.setOnClickListener {
            Log.d("LOGIN", "Attemps to login")
        }
    }

    public fun recupererEmail(): String{
        return etEmail.text.toString()
    }

    public fun recupererMotDePasse(): String{
        return etMotDePasse.text.toString()
    }

    public fun traiterConnexion(){

    }

    public fun redirigerVersListeChambres(){

    }
}