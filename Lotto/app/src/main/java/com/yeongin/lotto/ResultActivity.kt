package com.yeongin.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {

    val lottoImageStartId = R.drawable.ball_01

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getIntegerArrayListExtra("result")

        val name = intent.getStringExtra("name")

        val constellation = intent.getStringExtra("constellation")


        resultLabel.text = "랜덤으로 생성된\n 로또번호입니다"

        if(!TextUtils.isEmpty(name)){
            resultLabel.text = "${name} 님의 ${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())}\n로또번호입니다"
            
        }

        if(!TextUtils.isEmpty(constellation)){
            resultLabel.text = "${constellation} 의 \n${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())}\n로또번호입니다"

        }

        // ?연산자로 결과값 NULL인지 확인, 따라서 결과값이 있을 떄만 실행됨
        result?.let{
            updateLottoBallImage(result.sortedBy{it})
        }
    }


    fun updateLottoBallImage(result: List<Int>)
    {
        if(result.size < 6) return

        imageView01.setImageResource(lottoImageStartId+(result[0]-1))
        imageView02.setImageResource(lottoImageStartId+(result[1]-1))
        imageView03.setImageResource(lottoImageStartId+(result[2]-1))
        imageView04.setImageResource(lottoImageStartId+(result[3]-1))
        imageView05.setImageResource(lottoImageStartId+(result[4]-1))
        imageView06.setImageResource(lottoImageStartId+(result[5]-1))

    }
}