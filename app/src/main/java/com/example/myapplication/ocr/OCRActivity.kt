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
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import java.io.FileNotFoundException
import java.io.InputStream


@Suppress("DEPRECATION")
class OCRActivity : AppCompatActivity() {

    val REQUEST_CODE = 2
    private lateinit var binding: ActivityOcrBinding
    private lateinit var ocr_result_view: TextView
    private lateinit var ocr_start_button: Button

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOcrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageView = binding.imageView
        text_info = binding.textInfo
        recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())

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
            TextRecognition(recognizer!!)
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
                 data?:return
                 val uri = data.data as Uri
                 setImage(uri)
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

    lateinit var issueNum: String
    lateinit var date: String
    lateinit var time:String
    lateinit var where:String
    lateinit var userName:String

    private fun TextRecognition(recognizer: TextRecognizer) {
        val result: Task<Text> = recognizer.process(image!!) // 이미지 인식에 성공하면 실행되는 리스너
            .addOnSuccessListener { visionText ->
                Log.e("텍스트 인식", "성공")
                // Task completed successfully
                val resultText = visionText.text
                val s = resultText.split(": ")
                issueNum = s[1].split("\n")[0].replace(" ", "").replace("-", "_")
                date = s[5].split("\n")[0]
                time = s[6].split("\n")[0]
                where = s[8].split("\n")[0]
                userName = s[2].split("\n")[0]
                println(issueNum)
                var isValid = CheckValidityCoroutine.BackgroundTask(userName, issueNum, where, date, time)
                println(isValid)
            }
        // 이미지 인식에 실패하면 실행되는 리스너
    }
}