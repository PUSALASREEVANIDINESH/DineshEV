package com.example.evconnect.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.evconnect.R

class HomeFragment : Fragment() {

    lateinit var imgLetsTravel: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_home, container, false)
        imgLetsTravel = view.findViewById(R.id.letsTravel)
        imgLetsTravel.setOnClickListener {
            val TravelFragment= TravelFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout,TravelFragment)
                ?.addToBackStack("Home")
                ?.commit()
        }
        return view
    }


}