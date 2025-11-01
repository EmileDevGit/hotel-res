package com.leen.hotelres_app.Vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leen.hotelres_app.R

class ChambresFragment : Fragment() {
    private lateinit var chambres: ArrayList<String>
    private lateinit var chambresAdaptater: ChambresAdaptater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chambres, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chambres = arrayListOf("premier leitn", "premier leitn", "premier leitn", "premier leitn", "premier leitn", "premier leitn", "premier leitn")

        val chambresRecyclerView = activity?.findViewById<RecyclerView>(R.id.rv_chambres)

        chambresRecyclerView?.layoutManager = LinearLayoutManager(context)
        chambresAdaptater = ChambresAdaptater(chambres)
        chambresRecyclerView?.adapter = chambresAdaptater
    }
}