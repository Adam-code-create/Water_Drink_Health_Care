package uz.gita.waterdrinkhealthcare.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.*
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class ChangeWakeUpTimeDialog :DialogFragment(R.layout.dialog_wake_up_time_change) {
    private val binding by viewBinding(DialogWakeUpTimeChangeBinding::bind)

    private var wakeUpListener :((String)-> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        binding.numberPickerHour.minValue = 0
        binding.numberPickerHour.maxValue = 23
        binding.numberPickerHour.wrapSelectorWheel = true
        binding.numberPickerHour.setOnValueChangedListener { numberPicker, old, new ->
            if (new<10) pref.bedTimeHour = "0$new"
            else pref.bedTimeHour = new.toString()
            wakeUpListener?.invoke("${pref.bedTimeHour}:${pref.bedTimeMinute}")
        }
        binding.numberPickerMinute.minValue = 0
        binding.numberPickerMinute.maxValue = 59
        binding.numberPickerMinute.wrapSelectorWheel = true
        binding.numberPickerMinute.setOnValueChangedListener { numberPicker, old, new ->
            if (new<10) pref.bedTimeMinute = "0$new"
            else pref.bedTimeMinute = new.toString()
            wakeUpListener?.invoke("${pref.bedTimeHour}:${pref.bedTimeMinute}")
        }

        binding.okGenderBtn.setOnClickListener {
            dismiss()
        }
        binding.cancelGender.setOnClickListener {
            dismiss()
        }

    }
    fun setWakeUpListener (f :(String)->Unit){
        wakeUpListener = f
    }
}