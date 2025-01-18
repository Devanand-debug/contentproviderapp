package com.iav.contestdataprovider.data.repo

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.iav.contestdataprovider.data.model.RandomString
import org.json.JSONObject
import javax.inject.Inject

class RandomStringRepository @Inject constructor(private val contentResolver: ContentResolver) {

//    @RequiresApi(Build.VERSION_CODES.O)
//    suspend fun fetchRandomString(length: Int): RandomString {
//        return withContext(Dispatchers.IO) {
//            Log.e("btnClick1", "$length")
//            val uri = Uri.parse("content://com.iav.contestdataprovider/text")
//            val cursor = contentResolver.query(
//                uri, null, null, null,
//                Bundle().apply {
//                    putInt(ContentResolver.QUERY_ARG_LIMIT, length)
//                }.toString()
//            ) ?: throw Exception("Failed to query content provider")
//
//            cursor.use {
//                if (it.moveToFirst()) {
//                    val json = it.getString(it.getColumnIndexOrThrow("data"))
//                    val randomText = Gson().fromJson(json, RandomTextResponse::class.java).randomText
//                    RandomString(randomText.value, randomText.length, randomText.created)
//                } else {
//                    throw Exception("No data returned from content provider")
//                }
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchRandomString(maxLength: Int): RandomString? {
      //  val CONTENT_URI = Uri.parse("content://com.iav.contestdataprovider/maxLength")

      //  Log.e("btnClick2", "$CONTENT_URI")
        val length = arrayOf(maxLength.toString())
        val cursor: Cursor? = contentResolver.query(
            Uri.parse("content://com.iav.contestdataprovider/text?limit=$maxLength"),
            null,
            null,
            null,
            null
         //   maxLength.toString()
//            Bundle().apply {
//                putInt(ContentResolver.QUERY_ARG_LIMIT, maxLength)
//            },
        )
        Log.e("btnClick3", cursor.toString())
        Log.d("fetchRandomString", "Columns: ${cursor?.columnNames?.joinToString()}")

        cursor.use {
            if (it?.moveToFirst() == true) {
                val jsonString = it.getString(it.getColumnIndexOrThrow("data"))
                return parseJsonToRandomString(jsonString)
            }
        }
        return null
    }

    private fun parseJsonToRandomString(json: String): RandomString {
        Log.e("btnClick4", json)
        val jsonObject = JSONObject(json)
            val randomText = jsonObject.getJSONObject("randomText")
            return RandomString(
                value = randomText.getString("value"),
                length = randomText.getInt("length"),
                created = randomText.getString("created")
            )
    }
}
