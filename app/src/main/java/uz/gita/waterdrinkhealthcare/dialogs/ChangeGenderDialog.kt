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
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class ChangeGenderDialog :DialogFragment(R.layout.dialog_gender_change) {
    private val binding by viewBinding(DialogGenderChangeBinding::bind)

    private var genderListener :((String)-> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        binding.boy.setOnClickListener {
            pref.gender = "Male"
            genderListener?.invoke("Male")
        }
        binding.girl.setOnClickListener {
            pref.gender = "Female"
            genderListener?.invoke("Female")
        }
        binding.okGenderBtn.setOnClickListener {
            dismiss()
        }
        binding.cancelGender.setOnClickListener {
            dismiss()
        }
    }
    fun setGenderListener (f :(String)->Unit){
        genderListener = f
    }
}