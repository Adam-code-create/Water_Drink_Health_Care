package uz.gita.waterdrinkhealthcare.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.PageWeightIndicatorBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class WeightPage : Fragment(R.layout.page_weight_indicator) {
    private val binding by viewBinding(PageWeightIndicatorBinding::bind)
    private var listener :((String)->Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 200
        binding.numberPicker.wrapSelectorWheel = true
        binding.numberPicker.setOnValueChangedListener { numberPicker, old, new ->
            pref.weight = new.toString()
            listener?.invoke(new.toString())
        }
    }

    fun setListener (f: (String)->Unit){
        listener = f
    }
}