package uz.gita.waterdrinkhealthcare.screens

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.adapter.WaterAdapter
import uz.gita.waterdrinkhealthcare.data.DrunkWater
import uz.gita.waterdrinkhealthcare.database.MyDatabase
import uz.gita.waterdrinkhealthcare.databinding.ScreenMainBinding
import uz.gita.waterdrinkhealthcare.dialogs.SelectCupDialog
import uz.gita.waterdrinkhealthcare.reciever.MyReceiver
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref
import java.util.*
import kotlin.collections.ArrayList

class MainScreen :Fragment(R.layout.screen_main) {
    private val viewBinding by viewBinding(ScreenMainBinding::bind)
    private val database by lazy { MyDatabase.getDatabase().getDao() }
    private val listData by lazy {  ArrayList<DrunkWater>() }
    private val adapter by lazy { WaterAdapter(listData) }
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        notificationChannel()
        val pref = SharedPref.getShared()
        var cup = pref.drunkWater
        viewBinding.circularProgressBar.apply {
            progress = cup * 1f
            // or with animation
            setProgressWithAnimation(  -cup*1f, 2000) // =1s

            // Set Progress Max
            progressMax = pref.targetWater * 1f
            viewBinding.currentSituation.text = "${pref.drunkWater} ml"
        }
        viewBinding.listDrunkWater.adapter = adapter
        viewBinding.listDrunkWater.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.mainBtn.setOnClickListener {
            cup += pref.cupSize
            pref.drunkWater = cup
            viewBinding.circularProgressBar.apply {
                progress = cup * 1f
                // or with animation
                setProgressWithAnimation(  -cup*1f, 2000) // =1s
                // Set Progress Max
                progressMax = pref.targetWater * 1f

                viewBinding.currentSituation.text = "${pref.drunkWater} ml"

            }

            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val data =DrunkWater(0, "$hour:$minute", pref.cupSize)
            listData.add(data)
            database.insert(data)
            loadData()
            adapter.notifyDataSetChanged()
        }
        viewBinding.setting.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_screenSettings2)
        }
        viewBinding.changeCup.setOnClickListener {
            val dialog = SelectCupDialog()
            dialog.show(childFragmentManager, "dialog")
        }
        viewBinding.targetWater.text = "${pref.targetWater} ml"
        adapter.setListener { data, pos, more ->
            val popUp = PopupMenu(requireContext(),more)
            popUp.setOnMenuItemClickListener {
                if (it.itemId == R.id.edit) {
                    val options = arrayOf(
                       "100", "150", "200", "250", "300", "350","400", "450", "500")
                    val dialog = AlertDialog.Builder(requireContext())
                    dialog.setTitle("Choose the size")
                    dialog.setSingleChoiceItems(options,0) { dialogInterface, i: Int ->
                        val d = data.size
                        data.size = options[i].toInt()
                        cup += (data.size-d)
                        viewBinding.currentSituation.text = "$cup ml"
                        database.update(data)
                        pref.drunkWater = cup
                        adapter.notifyItemChanged(pos)
                        dialogInterface.dismiss()
                    }
                    dialog.setNeutralButton("Cancel"){listener, j :Int ->
                        listener.dismiss()
                    }
                    dialog.create().show()
                }
                else {
                    database.delete(data)
                    listData.remove(data)
                    adapter.notifyItemRemoved(pos)
                    cup -= data.size
                    viewBinding.currentSituation.text = "$cup ml"
                    pref.drunkWater = cup
                }
                true
            }
            popUp.inflate(R.menu.pop_up_menu)
            popUp.show()

        }

        calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 1
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] =0

        when(pref.reminder){
            0->  setReminder(calendar.timeInMillis,AlarmManager.INTERVAL_HOUR)
            1->  setReminder(calendar.timeInMillis,AlarmManager.INTERVAL_HOUR *2 )
            2->  setReminder(calendar.timeInMillis,AlarmManager.INTERVAL_DAY)
            3->  cancelAlarm()

        }


    }
    private fun cancelAlarm(){
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), MyReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0, intent, 0)
        alarmManager.cancel(pendingIntent)
    }
    private fun setReminder(alarmTime : Long,interval :Long ){
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), MyReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0, intent, 0)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime,interval, pendingIntent)
    }
    private fun notificationChannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            val name: CharSequence = "todoAppChannel"
            val description = "Channel for alarm manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("todoapp", name, importance)
            channel.description = description

            ContextCompat.getSystemService(
                requireContext(), NotificationManager::class.java
            )?.createNotificationChannel(channel)
        }
    }
    private fun loadData(){
        listData.clear()
        listData.addAll(database.getData())
        adapter.notifyDataSetChanged()
    }

}