package com.example.comprehensiveapplication.camera

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.comprehensiveapplication.R
import java.io.File


const val TAKE_PHOTO_ACTIVITY = 1
const val CHOOSE_PHOTO_ACTIVITY = 2

class CameraActivity : AppCompatActivity() {

    private var takePictureBtn: Button? = null
    private var selectFromAlbumBtn: Button? = null
    private var picture: ImageView? = null
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        takePictureBtn = findViewById<Button>(R.id.take_picture)
        selectFromAlbumBtn = findViewById<Button>(R.id.choose_album)
        picture = findViewById<ImageView>(R.id.picture)

        takePictureBtn!!.setOnClickListener {
            val outputImage = File(externalCacheDir, "${System.currentTimeMillis()}.jpg")
            outputImage.createNewFile()

            imageUri = if (Build.VERSION.SDK_INT >= 24) {
                FileProvider.getUriForFile(
                    this@CameraActivity,
                    "com.example.cameralusage.fileprovider", outputImage
                )
            } else {
                Uri.fromFile(outputImage)
            }

            //启动相机程序
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, TAKE_PHOTO_ACTIVITY)
        }

        selectFromAlbumBtn!!.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this@CameraActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@CameraActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                openAlbum()
            }
        }
    }

    private fun openAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, CHOOSE_PHOTO_ACTIVITY)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum()
            } else {
                Toast.makeText(this, "you denied the permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PHOTO_ACTIVITY -> if (resultCode == RESULT_OK) {
                try {
                    val bm = BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            imageUri!!
                        )
                    )
                    picture!!.setImageBitmap(bm)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            CHOOSE_PHOTO_ACTIVITY -> if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {  //4.4及以上的系统使用这个方法处理图片；
                    handleImageOnKitKat(data)
                } else {
                    handleImageBeforeKitKat(data) //4.4及以下的系统使用这个方法处理图片
                }
            }
            else -> {}
        }
    }

    private fun handleImageBeforeKitKat(data: Intent?) {
        val uri = data!!.data
        val imagePath = getImagePath(uri, null)
        displayImage(imagePath)
    }

    @SuppressLint("Range")
    private fun getImagePath(uri: Uri?, selection: String?): String? {
        var path: String? = null
        //通过Uri和selection来获取真实的图片路径
        val cursor = contentResolver.query(uri!!, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }

    private fun displayImage(imagePath: String?) {
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            picture!!.setImageBitmap(bitmap)
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 4.4及以上的系统使用这个方法处理图片
     *
     * @param data
     */
    @TargetApi(19)
    private fun handleImageOnKitKat(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果document类型的Uri,则通过document来处理
            val docID = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority) {
                val id = docID.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()[1] //解析出数字格式的id
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/piblic_downloads"),
                    java.lang.Long.valueOf(docID)
                )
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            //如果是content类型的uri，则使用普通方式使用
            imagePath = getImagePath(uri, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            //如果是file类型的uri，直接获取路径即可
            imagePath = uri.path
        }
        displayImage(imagePath)
    }
}