package com.iav.contestdataprovider.data.repo

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.iav.contestdataprovider.data.model.RandomString
import com.iav.contestdataprovider.data.model.RandomTextResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class RandomStringRepository @Inject constructor(private val contentResolver: ContentResolver) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchRandomString(length: Int): RandomString {
        return withContext(Dispatchers.IO) {
            Log.e("btnClick1", "$length")
//            val cursor = contentResolver.query(
//                uri, null, null, null,
//                Bundle().apply {
//                    putInt(ContentResolver.QUERY_ARG_LIMIT, length)
//                }.toString()
//            ) ?: throw Exception("Failed to query content provider")

            val cursor : Cursor?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val bundle = Bundle().apply {
                    putInt(ContentResolver.QUERY_ARG_LIMIT, length)
                }
                 cursor = contentResolver.query(
                    Uri.parse("content://com.iav.contestdataprovider/text"),
                    null,
                    null,
                    null,
                    bundle.toString()
                )
            } else {
                // For older Android versions
                 cursor = contentResolver.query(
                    Uri.parse("content://com.iav.contestdataprovider/text?limit=$length"),
                    null,
                    null,
                    null,
                    null
                )
            }

            cursor.use {
                if (it?.moveToFirst() == true) {
                    val json = it.getString(it.getColumnIndexOrThrow("data"))
                    val randomText = Gson().fromJson(json, RandomTextResponse::class.java).randomText
                    RandomString(randomText.value, randomText.length, randomText.created)
                } else {
                    throw Exception("No data returned from content provider")
                }
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun fetchRandomString(maxLength: Int): RandomString? {
//        val CONTENT_URI = Uri.parse("content://com.iav.contestdataprovider/maxLength")
//
//      //  Log.e("btnClick2", "$CONTENT_URI")
//        val length = arrayOf(maxLength.toString())
//        val cursor: Cursor? = contentResolver.query(
//            CONTENT_URI,
//            null,
//            null,
//            arrayOf(maxLength.toString()),
//            null
//         //   maxLength.toString()
////            Bundle().apply {
////                putInt(ContentResolver.QUERY_ARG_LIMIT, maxLength)
////            },
//        )
//        Log.e("btnClick3", cursor.toString())
//        Log.d("fetchRandomString", "Columns: ${cursor?.columnNames?.joinToString()}")
//
//        cursor.use {
//            if (it?.moveToFirst() == true) {
//                val jsonString = it.getString(it.getColumnIndexOrThrow("data"))
//                return parseJsonToRandomString(jsonString)
//            }
//        }
//        return null
//    }

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
