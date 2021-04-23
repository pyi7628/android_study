package com.yeongin.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_constellation.*
import java.util.*
import kotlin.collections.ArrayList

class ConstellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        goResultButton.setOnClickListener{

            val intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getLottoNumbersFromHash(
                makeConstellationString(datePicker.month, datePicker.dayOfMonth))))

            intent.putExtra("constellation", makeConstellationString(datePicker.month, datePicker.dayOfMonth))

            startActivity(intent)
        }

        textView3.text = makeConstellationString(datePicker.month, datePicker.dayOfMonth)

        val calendar = Calendar.getInstance()

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
            object:CalendarView.OnDateChangeListener, DatePicker.OnDateChangedListener{
            override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int){
                textView3.text = makeConstellationString(datePicker.month, datePicker.dayOfMonth)
            }

                override fun onSelectedDayChange(
                    view: CalendarView,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    TODO("Not yet implemented")
                }

            })




        }



    fun makeConstellationString(month: Int, day: Int): String{
        val target = "${month+1}${String.format("%02d", day)}".toInt()

        when(target){
            in 101..119 -> return "염소자리"
            in 120..218 -> return "물병자리"
            in 219..320 -> return "물고기자리"
            in 321..419 -> return "양자리"
            in 420..520 -> return "황소자리"
            in 521..621 -> return "쌍둥이자리"
            in 622..722 -> return "게자리"
            in 723..822 -> return "사자자리"
            in 823..923 -> return "처녀자리"
            in 924..1022 -> return "천칭자리"
            in 1023..1122 -> return "전갈자리"
            in 1123..1224 -> return "사수자리"
            in 1225..1231 -> return "염소자리"
            else -> return "기타별자리"


        }
    }
}