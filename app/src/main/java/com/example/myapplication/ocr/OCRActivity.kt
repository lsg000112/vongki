package com.example.myapplication.ocr

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityOcrBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.g
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.FileNotFoundException
import java.io.InputStream


@Suppress("DEPRECATION")
class OCRActivity : AppCompatActivity() {

    val REQUEST_CODE = 2
    private lateinit var binding: ActivityOcrBinding
    private lateinit var ocr_result_view: TextView
    private lateinit var ocr_start_button: Button
    private val userCollectionRef = Firebase.firestore.collection("users")
    private val auth = Firebase.auth

    var imageView // 갤러리에서 가져온 이미지를 보여줄 뷰
            : ImageView? = null
    var bitmap // 갤러리에서 가져온 이미지를 담을 비트맵
            : Bitmap? = null
    var image // ML 모델이 인식할 인풋 이미지
            : InputImage? = null
    var text_info // ML 모델이 인식한 텍스트를 보여줄 뷰
            : TextView? = null
    var btn_get_image: Button? = null
    var btn_detection_image // 이미지 가져오기 버튼, 이미지 인식 버튼
            :Button? = null
    var recognizer //텍스트 인식에 사용될 모델
            : TextRecognizer? = null
    var isImageUploaded : Boolean = false

    companion object{
        fun say(i : Boolean) : Boolean{
            println("asdf" + i)
            return i
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOcrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageView = binding.imageView
        recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())

        binding.backButton.setOnClickListener{finish()}

        // GET IMAGE 버튼
        btn_get_image = binding.btnGetImage;

        // IMAGE DETECTION 버튼
        btn_detection_image = binding.btnDetectionImage;


        val onBtnGetImageClickListner = View.OnClickListener {
            println("b1")
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            //intent.setType(MediaStore.Images.Media.CONTENT_TYPE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)

        }

        val onBtnDetectionImageClickListner = View.OnClickListener {
            println("b2")
            if(isImageUploaded == true) {
                TextRecognition(recognizer!!)
            }else{
                Toast.makeText(this, "앨범에서 이미지를 선택해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        btn_get_image!!.setOnClickListener(onBtnGetImageClickListner)
        btn_detection_image!!.setOnClickListener(onBtnDetectionImageClickListner)
    }

     @Deprecated("Deprecated in Java")
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         if (resultCode != Activity.RESULT_OK) {
             return
         }
         when (requestCode) {
             REQUEST_CODE -> {
                 data ?: return
                 val uri = data.data as Uri
                 setImage(uri)
                 isImageUploaded = true
                 // 이미지 URI를 가지고 하고 싶은거 하면 된다.
             }
             else -> {
                 Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
             }
         }
    }

    // uri를 비트맵으로 변환시킨후 이미지뷰에 띄워주고 InputImage를 생성하는 메서드
    private fun setImage(uri: Uri) {
        try {
            val `in`: InputStream? = contentResolver.openInputStream(uri)
            bitmap = BitmapFactory.decodeStream(`in`)
            imageView!!.setImageBitmap(bitmap)

            image = InputImage.fromBitmap(bitmap!!, 0)
            Log.e("setImage", "이미지 to 비트맵")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    var issueNum: String = ""
    var date: String = ""
    var time:String = ""
    var where:String = ""
    var userName:String = ""

    private fun TextRecognition(recognizer: TextRecognizer) {
        val result: Task<Text> = recognizer.process(image!!) // 이미지 인식에 성공하면 실행되는 리스너
            .addOnSuccessListener { visionText ->
                Log.e("텍스트 인식", "성공")
                // Task completed successfully
                val resultText = visionText.text
                val s = resultText.split(": ")
                try {
                issueNum = s[1].split("\n")[0].replace(" ", "").replace("-", "_")
                date = s[5].split("\n")[0]
                time = s[6].split("\n")[0]
                where = s[8].split("\n")[0]
                userName = s[2].split("\n")[0]
                    println(issueNum + date + time + where + userName)
                 } catch (e : Exception){
                     Toast.makeText(this,"올바르지 않은 봉사 확인서입니다.", Toast.LENGTH_SHORT).show()
                 }
                userCollectionRef.document(auth.uid!!).get().addOnSuccessListener { document ->
                    val array : List<String> = document.get("issueNumRecord") as List<String>
                    val timeArray : List<String> = document.get("issueNumRecord") as List<String>
                    if(userName != document.get("name").toString().substring(0, userName.length)){
                        println("name error" + userName + document.get("name").toString().split(" "))
                        Toast.makeText(this, "유효한 봉사 인증서가 아니에요!", Toast.LENGTH_SHORT).show()
                    }else if(array.contains(issueNum)){
                        Toast.makeText(this, "이미 등록된 봉사 기록입니다!", Toast.LENGTH_SHORT).show()
                    }else{
                        val isValid = CoroutineScope(Dispatchers.Main).launch {
                            val job = CoroutineScope(Dispatchers.IO).async {
                                if (checkValidity(userName, issueNum, where, time, date)) {
                                    println("유효성 검증 완료")
                                    return@async true
                                } else {
                                    println("유효성 검증 실패")
                                    throw NotValidException
                                }
                            }
                        }.start()
                        isValid.runCatching {
                        }.onSuccess {
                            val hour = time.split("시간")[0]
                            val min = time.split("시간")[1]
                            val h = hour.replace("[^0-9]".toRegex(), "")
                            val m = min.replace("[^0-9]".toRegex(), "")
                            val addMileage = Integer.parseInt(h) * 60 + Integer.parseInt(m)
                            userCollectionRef.document(auth.uid!!).get().addOnSuccessListener { document ->
                                if(document != null){
                                    userCollectionRef.document(auth.uid!!).update("mileage", (addMileage + Integer.parseInt(document.data?.get("mileage").toString())))
                                    userCollectionRef.document(auth.uid!!).update("currentMileage", (addMileage + Integer.parseInt(document.data?.get("currentMileage").toString())))
                                    userCollectionRef.document(auth.uid!!).update("issueNumRecord", FieldValue.arrayUnion(issueNum))
                                    userCollectionRef.document(auth.uid!!).update("whereRecord", FieldValue.arrayUnion(where.split("(")[0]))
                                    userCollectionRef.document(auth.uid!!).update("timeRecord", FieldValue.arrayUnion(time))
                                }
                                else{
                                    println("mileage parse update failed")
                                }
                                Toast.makeText(
                                    this,
                                    h +"시간 " + m + "분 봉사로 " + addMileage + "마일리지가 적립되었어요!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }.onFailure {
                            Toast.makeText(this, "봉사 확인서 검증에 실패했어요", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    fun checkValidity(userName:String, issueNum: String, where: String, time: String, date: String) : Boolean{
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
        throw NotValidException
    }
}

object NotValidException : Throwable() {

}
