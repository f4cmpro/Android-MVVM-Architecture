package com.example.testformobiledeveloper.data.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class ResponseBase : IResponse {

}