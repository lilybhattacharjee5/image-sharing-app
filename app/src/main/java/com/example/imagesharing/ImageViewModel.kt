package com.example.imagesharing
import android.util.Log
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.net.URL


private const val IMAGE_DATA_API = "https://image-sharing-api.herokuapp.com/image_sharing/api/v1.0/images"
var apiData: String = ""

object ImageViewModel {
    data class ImageData(
        val id: Int,
        val url: String,
        val title: String,
        val caption: String,
        val location: String
    ): Serializable

    fun fromApiDataOne(apiData: String, index: Int): ImageData? {
        val json = JSONObject(apiData)
        val jsonResponse: JSONArray = json.getJSONArray("images")
        if (jsonResponse.length() > index) {
            val image: JSONObject = jsonResponse.getJSONObject(index)
            val id = image.get("id") as Int
            val url = image.get("url") as String
            val title = image.get("title") as String
            val caption = image.get("caption") as String
            val location = image.get("location") as String
            return ImageData(id, url, title, caption, location)
        }
        return null
    }
 }

fun fetchImageDataAsync(index: Int): Deferred<ImageViewModel.ImageData?> {
    return GlobalScope.async {
        if (apiData == "") {
            apiData = URL(IMAGE_DATA_API).readText()
        }
        ImageViewModel.fromApiDataOne(apiData, index)
    }
}
