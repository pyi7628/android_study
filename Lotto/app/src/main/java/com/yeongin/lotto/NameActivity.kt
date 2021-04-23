package com.yeongin.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_name.*
import kotlin.collections.ArrayList

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        goButton.setOnClickListener{
            if(TextUtils.isEmpty(editText.text.toString())) return@setOnClickListener
            val intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getLottoNumbersFromHash(editText.text.toString())))

            intent.putExtra("name", editText.text.toString())

            startActivity(intent)
        }

        backButton.setOnClickListener{
            finish()
        }
    }


}