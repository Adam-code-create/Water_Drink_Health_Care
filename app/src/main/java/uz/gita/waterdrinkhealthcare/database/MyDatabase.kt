package uz.gita.waterdrinkhealthcare.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.waterdrinkhealthcare.app.App
import uz.gita.waterdrinkhealthcare.dao.MyDao
import uz.gita.waterdrinkhealthcare.data.DrunkWater

@Database(entities = [DrunkWater::class], version = 1)
abstract class MyDatabase  : RoomDatabase() {
    abstract fun getDao () : MyDao
companion object{
    private lateinit var instance : MyDatabase
    fun getDatabase () :MyDatabase{
        if (!::instance.isInitialized){
            instance = Room.databaseBuilder(App.instance, MyDatabase::class.java, "database")
                .allowMainThreadQueries()
                .build()
        }
        return instance
    }
}

}