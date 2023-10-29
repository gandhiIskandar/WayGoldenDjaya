package com.binamultimediaindonesia.waygoldendjaya.domain.model

import com.binamultimediaindonesia.waygoldendjaya.common.Constants.gson

data class User(
    val address: String,
    val created_at: String,
    val gender: String,
    val group_id: Int,
    val hotel_id: Int,
    val id: Int,
    val group: Group,
    val session: Session,
    val is_leader: Boolean,
    val name: String,
    val phone_number: String,
    val pin: String,
    val hotel:Hotel,
    val profile_image: String,
    val session_id: Int,
    val updated_at: String
)




