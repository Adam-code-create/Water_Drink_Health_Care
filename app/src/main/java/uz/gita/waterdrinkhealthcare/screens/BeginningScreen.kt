package uz.gita.waterdrinkhealthcare.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.ScreenBeginningBinding

class BeginningScreen :Fragment(R.layout.screen_beginning) {
    private val viewBinding by viewBinding(ScreenBeginningBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_beginningScreen_to_intoScreen)
        }

    }
}