package uz.gita.waterdrinkhealthcare.sharedPref

import android.content.Context
import uz.gita.waterdrinkhealthcare.app.App

class SharedPref private constructor(){
    private val pref = App.instance.getSharedPreferences("WaterDrink", Context.MODE_PRIVATE)
    companion object{
      private lateinit var instance :SharedPref

      fun getShared(): SharedPref{
          if (!::instance.isInitialized){
              instance = SharedPref()
          }
          return instance
      }
    }

    var isNew : Int
    get() = pref.getInt("isNew", 0)
    set(value) = pref.edit().putInt("isNew", value).apply()

    var gender :String?
    get() = pref.getString("gender", "--")
    set(value) = pref.edit().putString("gender", value).apply()

    var weight :String?
        get() = pref.getString("weight", "--")
        set(value) = pref.edit().putString("weight", value).apply()

    var wakeUpTimeHour :String?
        get() = pref.getString("wakeUpTimeHour", "--")
        set(value) = pref.edit().putString("wakeUpTimeHour", value).apply()
    var wakeUpTimeMinute :String?
        get() = pref.getString("wakeUpTimeMinute", "--")
        set(value) = pref.edit().putString("wakeUpTimeMinute", value).apply()

    var bedTimeHour :String?
        get() = pref.getString("bedTimeHour", "--")
        set(value) = pref.edit().putString("bedTimeHour", value).apply()
    var bedTimeMinute :String?
        get() = pref.getString("bedTimeMinute", "--")
        set(value) = pref.edit().putString("bedTimeMinute", value).apply()

    var cupSize : Int
        get() = pref.getInt("cupSize", 100)
        set(value) = pref.edit().putInt("cupSize", value).apply()

    var targetWater :Int
        get() = pref.getInt("targetWater", 2210)
        set(value) = pref.edit().putInt("targetWater", value).apply()

    var drunkWater :Int
        get() = pref.getInt("drunkWater", 0)
        set(value) = pref.edit().putInt("drunkWater", value).apply()

    var reminder :Int
        get() = pref.getInt("reminder", 0)
        set(value) = pref.edit().putInt("reminder", value).apply()


}