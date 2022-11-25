package com.example.myapplication.ocr

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class CheckValidityCoroutine {

    companion object {
        fun BackgroundTask(
            userName:String,
            issueNum:String,
            where:String,
            date:String,
            time:String
        ) : Boolean{
            var isValid : Boolean = false
            CoroutineScope(Dispatchers.Main).launch {
                val job = CoroutineScope(Dispatchers.IO).async {
                    if (checkValidity(userName, issueNum, where, time, date)) {
                        println("유효성 검증 완료")
                        return@async true
                    }
                    else {
                        println("유효성 검증 실패")
                        return@async false
                    }
                }
                isValid = job.await()
            }.start()
            println(isValid)
            return isValid
        }

        private fun checkValidity(userName:String, issueNum: String, where: String, time: String, date: String) : Boolean{
            val doc2: Document = Jsoup.connect("https://www.1365.go.kr/vols/P9330/srvcgud/cnfrmnIssu.do").data("schVltrNm", userName).data("schIssuNo", issueNum).get()
            println(time)
            println(where)
            println(date)
            val hour: String = doc2.getElementsByTag("dd").get(10).text()
            val date2: String = doc2.getElementsByTag("dd").get(8).text()
            var where2: String = doc2.getElementsByClass("tit_board_list").text()
            where2 = where2.substringBefore(")")
            val where1 = where.substringBefore(")")
            if (where1 == where2) {
                println("where 같음")
                if (hour == time.replace(" ", "").replace("총", "")) {
                    println("time 같음")
                    if (date2 == date.substring(0, 10).replace("-", ".")) {
                        println("date 같음")
                        println("유효성 검증 완료")
                        return true
                    }
                    else
                        println("$date!=$date2")
                }
                else{
                        println("$hour!=$time")
                    }
            } else
                println("$where1!=$where2")
            println("유효성 검증 실패")
            return false
        }
    }


}