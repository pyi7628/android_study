package com.yeongin.lotto

import java.text.SimpleDateFormat
import java.util.*

object LottoNumberMaker {

    fun getRandomLottoNumber(): Int{
        return Random().nextInt(45)+1
    }

    fun getRandomLottoNumbers(): MutableList<Int>{
        val lottoNumbers = mutableListOf<Int>()

        for(i in 1..6){

            var number = 0
            do{
                number= getRandomLottoNumber()

            }while(lottoNumbers.contains(number))

            lottoNumbers.add(number)
        }

        return lottoNumbers
    }

    fun getShuffleLottoNumbers(): MutableList<Int>
    {
        val list = mutableListOf<Int>()

        for(number in 1..45) {
            list.add(number)

        }

        list.shuffle()

        return list.subList(0, 6)
    }

    fun getLottoNumbersFromHash(name: String): MutableList<Int>{
        val list = mutableListOf<Int>()

        for(number in 1..45)
        {
            list.add(number)
        }

        val targetString = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date()) + name
        list.shuffle(Random(targetString.hashCode().toLong()))

        return list.subList(0, 6)
    }


}