package com.example.imagesharing

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.image_card.view.*

class ImageCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    init {
        LayoutInflater.from(context).inflate(R.layout.image_card, this, true)
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.image_card_attributes, 0, 0)
            title.text = typedArray.getString(R.styleable.image_card_attributes_titleText)
            location.text = typedArray.getString(R.styleable.image_card_attributes_location)

            typedArray.recycle()
        }
    }

    fun setTitle(titleText: String) {
        val title = findViewById<TextView>(R.id.title)
        title.text = titleText
    }

    fun setLocation(locationText: String) {
        val location = findViewById<TextView>(R.id.location)
        location.text = locationText
    }
}