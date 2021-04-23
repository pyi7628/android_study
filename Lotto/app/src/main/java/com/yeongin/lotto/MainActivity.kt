package com.yeongin.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 랜덤 번호 생성 카드 이벤트 리스너
        randomCard.setOnClickListener{

            val intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getRandomLottoNumbers()))

            startActivity(intent)
        }

        constellationCard.setOnClickListener{
            startActivity(Intent(this, ConstellationActivity::class.java))
        }

        nameCard.setOnClickListener{
            startActivity(Intent(this, NameActivity::class.java))
        }

    }





}