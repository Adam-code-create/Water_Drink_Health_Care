package uz.gita.waterdrinkhealthcare.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class SplashScreen : Fragment(R.layout.screen_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        val pref = SharedPref.getShared()
        handler.postDelayed({
            if (pref.isNew == 0){
                findNavController().navigate(R.id.action_splashScreen_to_beginningScreen)
            }else{
                findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
            }
        }, 2000)
    }
}