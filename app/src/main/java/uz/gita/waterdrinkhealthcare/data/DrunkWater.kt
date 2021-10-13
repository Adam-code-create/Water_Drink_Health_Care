package uz.gita.waterdrinkhealthcare.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrunkWater(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val time : String,
    var size :Int
)
