package com.example.andelaproject.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.andelaproject.R
import com.example.andelaproject.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.viewpager_tablout.*
import kotlinx.android.synthetic.main.viewpager_tablout.view.*
import kotlinx.android.synthetic.main.viewpager_tablout.view.tabLayout


class ViewPagerFragment: Fragment(R.layout.viewpager_tablout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            LearningLeadersFragment(),
            LeaderIQFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter
        val names: ArrayList<String> = arrayListOf("Learning Leaders","Skill IQ Leaders")
       TabLayoutMediator(tabLayout, viewPager){tab, position ->
         tab.text = names[position]
       }.attach()
        vpButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_submitFragment)
        }


    }

}