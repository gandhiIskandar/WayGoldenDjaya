package com.binamultimediaindonesia.waygoldendjaya.data.remote


import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import retrofit2.http.*

interface WayGoldenDjayaApi {

    @GET("user")
    suspend fun getUserData(id: String, @HeaderMap headers:Map<String, String>): User

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("name") name: String,
        @Field("pin") pin: String
    ): LoginDto

    @GET("schedules?option={sessionId}")
    suspend fun getSchedules(@HeaderMap headers: Map<String, String>,
                             @Path("sessionId") sessionId: String):List<Schedule>


    @GET("get-hcd")
    suspend fun getHomeScreenData(@HeaderMap headers:Map<String, String>):HomeScreenDto


    @GET("streaming")
    suspend fun getStreamingData(@HeaderMap headers:Map<String, String>):Streaming

}