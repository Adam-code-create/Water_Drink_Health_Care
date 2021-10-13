package uz.gita.waterdrinkhealthcare.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.ScreenLastToStartBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class LastToStartScreen : Fragment(R.layout.screen_last_to_start) {
    private val viewBinding by viewBinding (ScreenLastToStartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        viewBinding.assistant.visibility = View.GONE
        viewBinding.letsStart.visibility = View.GONE
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            viewBinding.assistant.visibility = View.VISIBLE
            viewBinding.letsStart.visibility = View.VISIBLE
            viewBinding.letsStart.setOnClickListener {
                pref.isNew = 1
                findNavController().navigate(R.id.action_lastToStartScreen_to_mainScreen)
            }
        }, 3500)
    }
}