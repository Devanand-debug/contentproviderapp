//package com.iav.contestdataprovider.database
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import org.json.JSONObject
//
//@Entity
//data class RandomString(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val value: String,
//    val length: Int,
//    val created: String
//) {
//    companion object {
//        fun fromJson(json: String): RandomString {
//            val jsonObject = JSONObject(json)
//            val randomText = jsonObject.getJSONObject("randomText")
//            return RandomString(
//                value = randomText.getString("value"),
//                length = randomText.getInt("length"),
//                created = randomText.getString("created")
//            )
//        }
//    }
//}
//
