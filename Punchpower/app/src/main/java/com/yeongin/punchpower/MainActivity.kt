package com.yeongin.punchpower

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    //최대 펀치력
    var maxPower = 0.0

    //측정 시작
    var isStart = false

    var startTime =0L

    //Sensor Object, lazy로 실제 사용할 때 초기화!
    val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    //sensor event 처리
    val eventListener: SensorEventListener = object : SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }
        override fun onSensorChanged(event: SensorEvent?){
            event?.let{
                if(event.sensor.type != Sensor. TYPE_LINEAR_ACCELERATION) return@let

                val power =
                    Math.pow(event.values[0].toDouble(), 2.0) + Math.pow(event.values[1].toDouble(), 2.0) + Math.pow(event.values[2].toDouble(), 2.0)

                if(power>20&&!isStart){
                    startTime = System.currentTimeMillis()

                    isStart = true

                }

                if(isStart){

                    imageView.clearAnimation()

                    if(maxPower < power) maxPower = power

                    stateLabel.text = "펀치력을 측정하고 있습니다"

                    if(System.currentTimeMillis()-startTime > 3000){
                        isStart= false
                        punchPowerTestComplete(maxPower)

                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onStart(){
        super.onStart()
        initGame()
    }

    fun initGame(){
        maxPower = 0.0
        isStart = false
        startTime= 0L
        stateLabel.text= "핸드폰을 손에쥐고 주먹을 내지르세요"


        sensorManager.registerListener(
            eventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
            SensorManager.SENSOR_DELAY_NORMAL
        )


        val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.tran)
        imageView.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }
        })
    }

    fun punchPowerTestComplete(power: Double){
        Log.d("MainActivity", "측정완료: power: ${String.format("%.5f", power)}")

        sensorManager.unregisterListener(eventListener)
        val intent = Intent(this@MainActivity, ResultActivity::class.java)

        intent.putExtra("power", power)
        startActivity(intent)

    }

    override fun onStop(){
        super.onStop()
        try{
            sensorManager.unregisterListener(eventListener)

        }catch (e: Exception){}
    }
}

