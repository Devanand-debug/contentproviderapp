package com.iav.contestdataprovider.data.model

import java.util.UUID

//data class RandomString(
//    val value: String,
//    val length: Int,
//    val created: String
//)

data class RandomString(
    val value: String,
    val length: Int,
    val created: String,
    val id: String = UUID.randomUUID().toString()
)

data class RandomTextResponse(
    val randomText: RandomString
)

