package com.example.evconnect.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.evconnect.R
import com.example.evconnect.adapter.ViewPagerAdapter


class TravelFragment : Fragment() {

    lateinit var travelViewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_travel, container, false)
        travelViewPager = view.findViewById(R.id.travelViewPager)
        setUpTabs()
        return view
    }
    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        adapter.addFragment(FindFragment(),"Find")
        adapter.addFragment(ShareFragment(),"Share")
        adapter.addFragment(ChatFragment(),"Chat")
        adapter.addFragment(YourRidesFragment(),"Your Rides")
        travelViewPager.adapter=adapter
    }
}