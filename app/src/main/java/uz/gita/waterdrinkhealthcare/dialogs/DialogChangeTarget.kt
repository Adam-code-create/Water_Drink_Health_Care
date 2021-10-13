package uz.gita.waterdrinkhealthcare.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.DialogTargetChangeBinding
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class DialogChangeTarget :DialogFragment(R.layout.dialog_target_change) {
    private val binding by viewBinding(DialogTargetChangeBinding::bind)

    private var targetListener :((String)-> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                binding.newTarget.text = (progress*10).toString()
                pref.targetWater = progress*10
                targetListener?.invoke("${(progress*10)} ml")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.btnOkTarget.setOnClickListener {
            dismiss()
        }
        binding.btnCancelTarget.setOnClickListener {
            dismiss()
        }

    }
    fun setTargetListener (f :(String)->Unit){
        targetListener = f
    }
}