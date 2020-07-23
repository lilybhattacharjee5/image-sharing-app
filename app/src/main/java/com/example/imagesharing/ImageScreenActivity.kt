package com.example.imagesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageScreenActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_screen)

        val url = intent.getStringExtra("url")

        val image = findViewById<ImageView>(R.id.full_image)
        Picasso.get().load(url).into(image)
    }
}