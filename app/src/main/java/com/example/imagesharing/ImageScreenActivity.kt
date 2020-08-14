package com.example.imagesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ImageScreenActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_screen)

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        val location = intent.getStringExtra("location")
        val caption = intent.getStringExtra("caption")

        val image = findViewById<ImageView>(R.id.full_image)
        Picasso.get().load(url).into(image)

        val image_title = findViewById<TextView>(R.id.image_title)
        image_title.text = title

        val image_location = findViewById<TextView>(R.id.image_location)
        image_location.text = location

        val image_caption = findViewById<TextView>(R.id.image_caption)
        image_caption.text = caption
    }
}