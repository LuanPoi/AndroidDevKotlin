package com.example.togray

import android.content.res.AssetManager
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClicked(v: View){
        var imageView: ImageView = findViewById(R.id.imageView)
        var assetManager: AssetManager = this.assets

        try {
            var inputStream = assetManager.open("meme_caixao.jpeg")
            var image: Bitmap = BitmapFactory.decodeStream(inputStream)

            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(0f) //Remove Colour

            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            val paint = Paint()
            paint.colorFilter = colorFilter

            val width: Int = image.width
            val height: Int = image.height
            var image2: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            var canvas: Canvas = Canvas(image2)
            canvas.drawBitmap(image, 0F,0F, paint)
            imageView.setImageBitmap(image2)
        }catch (e: Exception){
            e.printStackTrace()
        }
        Log.d("bla","Bla")
    }
}
