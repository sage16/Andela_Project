package com.example.andelaproject.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.andelaproject.R

class SplashFragment: Fragment(R.layout.splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            findNavController().navigate(
                R.id.action_splashFragment_to_viewPagerFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment,true).build()
            )
        }, 3000)
    }
}