package uz.gita.waterdrinkhealthcare.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.PageGenderSelectorBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class GenderSelectorPage :Fragment(R.layout.page_gender_selector) {
    private val binding by viewBinding(PageGenderSelectorBinding::bind)
    private var listener :((String)->Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        binding.boy.setOnClickListener {
            pref.gender = "Male"
            listener?.invoke(pref.gender!!)
        }
        binding.girl.setOnClickListener {
            pref.gender = "Female"
            listener?.invoke(pref.gender!!)
        }
    }
    fun setListener(f:(String)->Unit){
        listener = f
    }
}