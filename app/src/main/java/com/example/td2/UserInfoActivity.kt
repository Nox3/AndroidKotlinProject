package com.example.td2

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.td2.network.Api
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.header_fragment.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.io.InputStream
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class UserInfoActivity : AppCompatActivity() {
    companion object {
        const val CAMERA_PERMISSION_CODE = 1000
        const val CAMERA_REQUEST_CODE = 2001
        const val IMAGE=1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

        val colorBar=sharedpreferences.getString("ColorBar", "")
        // val mybar =(activity as AppCompatActivity).supportActionBar
        val mybar= supportActionBar
        if(title!=null){
            mybar?.setTitle(title)}
        if(colorBar!= null){
            mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))}
        val button = findViewById<Button>(R.id.take_picture_button)
        button.setOnClickListener(View.OnClickListener { askCameraPermissionAndOpenCamera() })
        val upload_button=upload_image_button
        upload_button.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, ""), IMAGE)
        }
    }



    private fun askCameraPermissionAndOpenCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE )
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE )
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            openCamera()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){
        when(requestCode){
            CAMERA_PERMISSION_CODE -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    openCamera();
                }
                else {
                    Toast.makeText(this, "We need access to your camera to take a picture :'(", Toast.LENGTH_LONG).show()
                }
                return
            }
            else -> {}
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    /*private fun handlePhotoTaken(data: Intent?) {
        val image = data?.extras?.get("data") as? Bitmap
        val imageBody = imageToBody(image)

        // Afficher l'image
        val imageViewA = findViewById<ImageView>(R.id.imageView2)
        Glide.with(this).load(imageBody).into(imageViewA)

        // Plus tard : Envoie    de l'avatar au serveur
    }*/
    private fun handlePhotoTaken(data: Intent?) {
        val image = data?.extras?.get("data") as? Bitmap
        val imageBody = imageToBody(image)

        Glide.with(this).load(imageBody).fitCenter().into(imageView)

        if(imageBody == null) return
        MainScope().launch {
            Api/*.INSTANCE*/.userService.updateAvatar(imageBody)
        }

    }
    private fun handleChosenPicture(data: Intent?) {
        if(data?.data == null) return
        val inputStream: InputStream? = contentResolver.openInputStream(data.data!!)
        val bmp = BitmapFactory.decodeStream(inputStream)



    }




    private fun imageToBody(image: Bitmap?): MultipartBody.Part? {
        val f = File(cacheDir, "tmpfile.jpg")
        f.createNewFile()
        try {
            val fos = FileOutputStream(f)
            image?.compress(Bitmap.CompressFormat.PNG, 100, fos)

            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()

        }

        val body = RequestBody.create(MediaType.parse("image/png"), f)
        return MultipartBody.Part.createFormData("avatar", f.path ,body)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            handlePhotoTaken(data)
        }
        else {
            handleChosenPicture(data)

        }
    }
    override fun onRestart() {
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

        val colorBar=sharedpreferences.getString("ColorBar", "")
        // val mybar =(activity as AppCompatActivity).supportActionBar
        val mybar= supportActionBar
        if(title!=null){
            mybar?.setTitle(title)}
        if(colorBar!= null){
            mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))}
        super.onRestart()
    }

}
