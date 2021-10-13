package uz.gita.waterdrinkhealthcare.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.data.DrunkWater

class WaterAdapter (private val list : ArrayList<DrunkWater>):RecyclerView.Adapter<WaterAdapter.Vh>() {
    private var listener : ((DrunkWater, Int, View)->Unit)? = null
    inner class Vh (view:View) :RecyclerView.ViewHolder(view){
        private val more = view.findViewById<ImageView>(R.id.more)
        private val time = view.findViewById<TextView>(R.id.waterDrunkTime)
        private val size = view.findViewById<TextView>(R.id.drunkWaterSize)
        init {
            more.setOnClickListener {
                listener?.invoke(list[absoluteAdapterPosition], absoluteAdapterPosition, more)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(){
            val data = list[absoluteAdapterPosition]
            time.text = data.time
            size.text = "${data.size} ml"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Vh =
        Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_drunk_water,parent, false))


    override fun onBindViewHolder(holder: Vh, p1: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    fun setListener(f :(DrunkWater, Int, View)->Unit){
        listener = f
    }
}