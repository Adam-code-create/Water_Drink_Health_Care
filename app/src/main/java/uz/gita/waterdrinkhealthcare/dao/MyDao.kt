package uz.gita.waterdrinkhealthcare.dao

import androidx.room.*
import androidx.room.Dao
import uz.gita.waterdrinkhealthcare.data.DrunkWater

@Dao
interface MyDao {


    @Query("SELECT * FROM DrunkWater")
    fun getData () :List <DrunkWater>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data : DrunkWater)

    @Update
    fun update (data: DrunkWater)

    @Delete
    fun delete (data: DrunkWater)
}