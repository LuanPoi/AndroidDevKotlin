package com.example.togray

import android.content.res.AssetManager
import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

            var canvas: Canvas = Canvas(image)
            canvas.drawBitmap(image, 0F,0F, paint)
            imageView.setImageBitmap(image)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
