package uz.gita.waterdrinkhealthcare.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrinkhealthcare.R
import uz.gita.waterdrinkhealthcare.adapter.PageAdapter
import uz.gita.waterdrinkhealthcare.databinding.ScreenIntroBinding
import uz.gita.waterdrinkhealthcare.pages.*
import uz.gita.waterdrinkhealthcare.sharedPref.SharedPref

class IntoScreen : Fragment(R.layout.screen_intro) {
private val viewBinding by viewBinding(ScreenIntroBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        val genderPage = GenderSelectorPage()
        val weightPage = WeightPage()
        val bedTimePage = BedTimePage()
        val wakeUpTime = WakeUpPage()
        viewBinding.pager.adapter = PageAdapter(requireActivity(),genderPage, weightPage,wakeUpTime, bedTimePage)
        viewBinding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position==0){
                    viewBinding.back.visibility = View.GONE
                } else  viewBinding.back.visibility = View.VISIBLE
                if(position==3){
                    viewBinding.next.setOnClickListener {
                        findNavController().navigate(R.id.action_intoScreen_to_lastToStartScreen)
                    }
                }else{
                    viewBinding.next.setOnClickListener {
                        viewBinding.pager.currentItem =  viewBinding.pager.currentItem + 1
                    }
                }
                when (position) {
                    0 -> {
                        viewBinding.gender.setImageResource(R.drawable.ic_lebel)
                        viewBinding.genderChoose.visibility =View.VISIBLE
                        viewBinding.genderChoose.text = pref.gender
                    }
                    1 -> {
                        viewBinding.weight.setImageResource(R.drawable.ic_lebel)
                        viewBinding.weightChoose.visibility =View.VISIBLE
                        viewBinding.weightChoose.text = pref.weight
                    }
                    2 -> {
                        viewBinding.sleepTime.setImageResource(R.drawable.ic_lebel)
                        viewBinding.bedTimeChoose.visibility = View.VISIBLE
                        viewBinding.bedTimeChoose.text = "${pref.bedTimeHour}:${pref.bedTimeMinute}"
                    }
                    else -> {
                        viewBinding.wakeUpTime.setImageResource(R.drawable.ic_lebel)
                        viewBinding.wakeTimeChoose.visibility = View.VISIBLE
                        viewBinding.wakeTimeChoose.text ="${pref.wakeUpTimeHour}:${pref.wakeUpTimeMinute}"
                    }
                }
                viewBinding.back.setOnClickListener {
                    viewBinding.pager.currentItem =  viewBinding.pager.currentItem - 1
                }

            }

        })

        genderPage.setListener {
            viewBinding.genderChoose.text = it
        }

        weightPage.setListener {
            viewBinding.weightChoose.text = it
        }
        bedTimePage.setListener {
            viewBinding.bedTimeChoose.text = it
        }
        wakeUpTime.setListener {
            viewBinding.wakeTimeChoose.text =it
        }

    }
}