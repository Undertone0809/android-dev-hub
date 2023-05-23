package com.example.comprehensiveapplication.multithreadandnetwork

import android.app.AlertDialog
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.comprehensiveapplication.R
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL



class DownloadNetworkImageActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_network_image)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnDownload = findViewById<Button>(R.id.download)
        val exitButton = findViewById<Button>(R.id.exit)
        val downloadStatus = findViewById<TextView>(R.id.downloadStatus)
        val editTextURL = findViewById<EditText>(R.id.editTextURL)

        // exit program
        exitButton.setOnClickListener {
            Log.i(tag, "click exit button")
            finish()
        }

        btnDownload.setOnClickListener {
            Log.i(tag, "click download button")
            val dialog = AlertDialog.Builder(this@DownloadNetworkImageActivity)
                .setMessage("正在下载，请等待……")
                .setCancelable(false)
                .show()


            // 利用协程Coroutine下载图片数据
            GlobalScope.launch(Dispatchers.Main) {
                Log.i(tag, "downloading...")
                val bitmap = withContext(Dispatchers.IO) {
                    downloadImage(editTextURL.text.toString())
                }
                // 关闭等待对话框
                Log.i(tag, "finish download")
                imageView.setImageBitmap(bitmap)
                downloadStatus.text = "status: Finish"
                // todo timeout optimize
                dialog.dismiss()
            }
        }
    }

    private fun downloadImage(url: String): Bitmap? {

        return try {
            val inputStream = URL(url).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}