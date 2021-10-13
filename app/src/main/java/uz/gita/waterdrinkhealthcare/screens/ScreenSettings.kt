package uz.gita.waterdrinkhealthcare.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.BuildConfig
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.databinding.DialogTargetChangeBinding
import uz.gita.waterdrinkhealthcare.databinding.ScreenSettingsBinding
import uz.gita.waterdrinkhealthcare.dialogs.*
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class ScreenSettings :Fragment(R.layout.screen_settings) {
    private val viewBinding by viewBinding(ScreenSettingsBinding::bind)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        viewBinding.settingWaterTarget.text = "${pref.targetWater} ml"
        viewBinding.genderText.text = pref.gender
        viewBinding.weightText.text = pref.weight
        viewBinding.sleepingTimeText.text = "${pref.bedTimeHour}:${pref.bedTimeMinute}"
        viewBinding.wakeUpTimeText.text = "${pref.wakeUpTimeHour}:${pref.wakeUpTimeMinute}"

        viewBinding.backToMain.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.setTarget.setOnClickListener {
            val dialog = DialogChangeTarget()
            dialog.setTargetListener {
                viewBinding.settingWaterTarget.text = it
            }
            dialog.show(childFragmentManager, "dialog")
        }

        viewBinding.changeGender.setOnClickListener {
            val dialog = ChangeGenderDialog()
            dialog.setGenderListener {
                viewBinding.genderText.text = pref.gender
            }
            dialog.show(childFragmentManager, "gender")
        }

        viewBinding.changeWeight.setOnClickListener {
            val dialog = ChangeWeightDialog()
            dialog.setWeightListener {
                viewBinding.weightText.text = it
            }
            dialog.show(childFragmentManager, "weight")
        }

        viewBinding.changeSleepingTime.setOnClickListener {
            val dialog = ChangeBedTimeDialog()
            dialog.setBedTimeListener {
                viewBinding.sleepingTimeText.text = it
            }
            dialog.show(childFragmentManager,"bedTime")
        }

        viewBinding.changeWakeUpTime.setOnClickListener {
            val dialog = ChangeWakeUpTimeDialog()
            dialog.setWakeUpListener {
                viewBinding.wakeUpTimeText.text = it
            }
            dialog.show(childFragmentManager,"wakeUpTime")
        }

        viewBinding.share.setOnClickListener {
            val packageNme = BuildConfig.APPLICATION_ID
         //   val appName = requireContext().getString(R.string.app_name)
            val text = "Install this cool Application"
            val link = "$text  https://play.google.com/store/apps/details?id=$packageNme"
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, link)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intent, "Share link")
           startActivity(shareIntent)
            /*
      //Google storedagi appga yonaltirib yuboradi

      *  val intent = Intent(Intent.ACTION_VIEW)
          intent.data = Uri.parse("https://play.google.com/store/apps/details?id=uz.gita.noteapp")
          startActivity(intent)
      * */

        }

        viewBinding.restart.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setCancelable(false)
            dialog.setTitle("Do rou really reset your data? ")
            dialog.setPositiveButton("Ok") { dialogInterface, pos ->
                pref.isNew = 0
                pref.gender = "--"
                pref.wakeUpTimeMinute= "--"
                pref.cupSize = 100
                pref.wakeUpTimeHour = "--"
                pref.bedTimeMinute = "--"
                pref.bedTimeHour = "--"
                pref.targetWater = 2210
                pref.weight = "--"
                findNavController().navigate(R.id.action_screenSettings_to_splashScreen)
            }
            dialog.setNegativeButton("No"){dialogInterface, pos -> }
            dialog.show()

        }

        viewBinding.setReminder.setOnClickListener {
            val options = arrayOf(
                "Every hour"," Every two hours", "Once a day", "Turn off the reminder")
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("Choose the size")
            dialog.setSingleChoiceItems(options,0) { dialogInterface, i: Int ->
                pref.reminder =i
                viewBinding.reminderImage.setImageResource(R.drawable.ic_notifications_off)
                dialogInterface.dismiss()
            }
            dialog.setNeutralButton("Cancel"){listener, j :Int ->
                listener.dismiss()
            }
            dialog.create().show()

        }








    }
}