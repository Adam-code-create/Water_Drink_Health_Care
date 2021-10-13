package uz.gita.waterdrinkhealthcare.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.DialogGenderChangeBinding
import uz.gita.waterdrinkhealthcare.databinding.DialogTargetChangeBinding
import uz.gita.waterdrinkhealthcare.databinding.DialogWeightChangeBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class ChangeWeightDialog :DialogFragment(R.layout.dialog_weight_change) {
    private val binding by viewBinding(DialogWeightChangeBinding::bind)

    private var weightListener :((String)-> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 200
        binding.numberPicker.wrapSelectorWheel = true
        binding.numberPicker.setOnValueChangedListener { numberPicker, old, new ->
            pref.weight = new.toString()
            weightListener?.invoke(new.toString())
        }
        binding.okGenderBtn.setOnClickListener {
            dismiss()
        }
        binding.cancelGender.setOnClickListener {
            dismiss()
        }

    }
    fun setWeightListener (f :(String)->Unit){
        weightListener = f
    }
}