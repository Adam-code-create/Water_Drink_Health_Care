package uz.gita.waterdrinkhealthcare.dialogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.DialogChangeCupBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class SelectCupDialog :DialogFragment(R.layout.dialog_change_cup) {
    private val binding by viewBinding(DialogChangeCupBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()

        binding.size100.setOnClickListener {
            pref.cupSize = 100
        }
        binding.size150.setOnClickListener {
            pref.cupSize = 150
        }
        binding.size200.setOnClickListener {
            pref.cupSize = 200
        }
        binding.size250.setOnClickListener {
            pref.cupSize = 250
        }
        binding.size300.setOnClickListener {
            pref.cupSize = 300
        }
        binding.size400.setOnClickListener {
            pref.cupSize = 400
        }
        binding.size500.setOnClickListener {
            pref.cupSize = 500
        }

        binding.customize.setOnClickListener {
          //  pref.cupSize = 500
        }

        binding.btnOk.setOnClickListener {
           dismiss()
        }
        binding.btnCancel.setOnClickListener {
           dismiss()
        }

    }
}