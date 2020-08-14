package com.example.imagesharing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageDataset = mutableListOf<ImageViewModel.ImageData>()

        viewManager = LinearLayoutManager(this)
        viewAdapter = ImageAdapter(this, imageDataset) // extend recycler view adapter

        GlobalScope.launch {
            val data = fetchImageDataAsync()
            imageDataset.addAll(data)
            runOnUiThread {
                viewAdapter.notifyDataSetChanged()
            }
        }

        imageRecyclerView = findViewById<RecyclerView>(R.id.all_images).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun addImageActivity(view: View) {
        val intent = Intent(this, AddImageActivity::class.java)
        startActivity(intent)
    }
}

class ImageAdapter(private val activity: MainActivity, private val imageDataset: List<ImageViewModel.ImageData>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(val imageCard: View) : RecyclerView.ViewHolder(imageCard)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // create a new view
        val imageCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(imageCard)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageCardView = holder.imageCard
        val title = imageCardView.findViewById<TextView>(R.id.title)
        val location = imageCardView.findViewById<TextView>(R.id.location)
        val imageView = imageCardView.findViewById<ImageView>(R.id.image)
        val currImageData = imageDataset[position]
        title.text = currImageData.title
        location.text = currImageData.location
        Picasso.get().load(currImageData.url).resize(150, 100).centerCrop().into(imageView)
        holder.itemView.setOnClickListener {
            val imageScreenIntent = Intent(activity, ImageScreenActivity::class.java)
            imageScreenIntent.putExtra("url", currImageData.url)
            imageScreenIntent.putExtra("title", currImageData.title)
            imageScreenIntent.putExtra("location", currImageData.location)
            imageScreenIntent.putExtra("caption", currImageData.caption)
            activity.startActivity(imageScreenIntent)
        }
    }

    override fun getItemCount() = imageDataset.size
}
