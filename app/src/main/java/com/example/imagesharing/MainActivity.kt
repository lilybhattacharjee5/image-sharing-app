package com.example.imagesharing

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
//    private var imageData: Collection<ImageViewModel.ImageData> = emptyList()
//    private lateinit var imageData: ImageViewModel.ImageData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            for (i in 0..2) {
                val currImageData = fetchImageDataAsync(i).await()
                if (currImageData != null) {
                    displayImageData(currImageData, i)
                }
            }
        }
    }

    fun displayImageData(imageData: ImageViewModel.ImageData, index: Int) {
        val newImageCard = ImageCard(this)
        main.addView(newImageCard)
        newImageCard.setTitle(imageData.title)
        newImageCard.setLocation(imageData.location)
    }
}

//curl -i -H "Content-Type: application/json" -X POST -d '{"url": "https://www.canyontours.com/wp-content/uploads/2014/08/las-vegas-strip-at-night-aerial.jpg”, “title”: “Night in Las Vegas”, “location”: “Las Vegas”}’ http://image-sharing-api.herokuapp.com/image_sharing/api/v1.0/images
