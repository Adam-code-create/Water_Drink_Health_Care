package uz.gita.waterdrinkhealthcare.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter (
    activity: FragmentActivity,
private val genderPage :Fragment,
private val weightPage :Fragment,
private val wakeUpPage :Fragment,
private val bedTimePage :Fragment) :FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
      return when(position){
           0->genderPage
           1->weightPage
           2->bedTimePage
           else->wakeUpPage
       }
    }
}