package com.example.imagesharing
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.net.URL


private const val IMAGE_DATA_API = "https://image-sharing-stage.herokuapp.com/image_sharing/api/v1.0/images"
var apiData: String = ""

object ImageViewModel {
    data class ImageData(
        val id: Int,
        val url: String,
        val title: String,
        val caption: String,
        val location: String
    ): Serializable

    fun fromApiData(apiData: String): List<ImageData> {
        val json = JSONObject(apiData)
        val jsonResponse: JSONArray = json.getJSONArray("images")
        val images = mutableListOf<ImageData>()
        for (index in 0..jsonResponse.length() - 1) {
            val image: JSONObject = jsonResponse.getJSONObject(index)
            val id = image.get("id") as Int
            val url = image.get("url") as String
            val title = image.get("title") as String
            val caption = image.get("caption") as String
            val location = image.get("location") as String
            images.add(index, ImageData(id, url, title, caption, location))
        }
        return images
    }
 }

fun fetchImageDataAsync(): List<ImageViewModel.ImageData> {
    if (apiData == "") {
        apiData = URL(IMAGE_DATA_API).readText()
    }
    return ImageViewModel.fromApiData(apiData)
}
