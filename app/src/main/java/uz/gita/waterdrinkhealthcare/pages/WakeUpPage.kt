package uz.gita.waterdrinkhealthcare.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.PageWakeupTimeBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class WakeUpPage:Fragment(R.layout.page_wakeup_time) {
   private val binding by viewBinding (PageWakeupTimeBinding::bind)
    private var listener :((String)->Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = SharedPref.getShared()
        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = 23
        binding.hourPicker.wrapSelectorWheel = true
        binding.hourPicker.setOnValueChangedListener { numberPicker, old, new ->
            if (new<10) pref.wakeUpTimeHour = "0$new"
            else pref.wakeUpTimeHour = new.toString()
            listener?.invoke("${pref.wakeUpTimeHour}:${pref.wakeUpTimeMinute}")
        }
        binding.minutePicker.minValue = 0
        binding.minutePicker.maxValue = 59
        binding.minutePicker.wrapSelectorWheel = true
        binding.minutePicker.setOnValueChangedListener { numberPicker, old, new ->
            if (new<10) pref.wakeUpTimeMinute = "0$new"
            else pref.wakeUpTimeMinute= new.toString()
            listener?.invoke("${pref.wakeUpTimeHour}:${pref.wakeUpTimeMinute}")
        }



    }
    fun setListener(f:(String)->Unit){
        listener = f
    }
}