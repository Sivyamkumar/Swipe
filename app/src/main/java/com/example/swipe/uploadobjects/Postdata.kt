package com.example.swipe.uploadobjects

data class PostData(
    val message: String,
    val product_details: Productdetails,
    val product_id: Int,
    val success: Boolean
)