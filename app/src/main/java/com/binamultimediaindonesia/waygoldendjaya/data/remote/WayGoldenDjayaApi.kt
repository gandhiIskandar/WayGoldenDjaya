package com.binamultimediaindonesia.waygoldendjaya.data.remote


import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.ResponseDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SchedulesDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface WayGoldenDjayaApi {

    @GET("get_user")
    suspend fun getUserData(@Query("id") id: String, @HeaderMap headers:Map<String, String>): User

    @FormUrlEncoded
    @POST("user_update/{idUser}")
    suspend fun updateUser(@Path("idUser") id:String, @FieldMap field:Map<String, String>, @HeaderMap header:Map<String, String>):ResponseDto

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("name") name: String,
        @Field("pin") pin: String
    ): LoginDto

    @FormUrlEncoded
    @POST("update_pin")
    suspend fun updatePIN(
        @Field("new_pin") pin: String,
        @HeaderMap header: Map<String, String>
    ): ResponseDto


    @POST("logout")
    suspend fun logout(@HeaderMap headerMap: Map<String,String>) :ResponseDto

    @GET("schedulesApi")
    suspend fun getSchedules(@HeaderMap headers: Map<String, String>,
                             @Query("id_session") sessionId: String):SchedulesDto


@GET("destination/{id_destination}")
suspend fun getDestination(@HeaderMap headers: Map<String, String>,
                           @Path("id_destination") id: String):Destination

    @Multipart
    @POST("update_profile_image")
    suspend fun updatePhoto(
        @HeaderMap headers: Map<String, String>,
        @Part image: MultipartBody.Part
    ):ResponseDto

    @POST("get_hcd")
    suspend fun getHcd(@HeaderMap headers: Map<String, String>):LoginDto




//    @GET("get-hcd")
//    suspend fun getHomeScreenData(@HeaderMap headers:Map<String, String>):HomeScreenDto


    @GET("streaming")
    suspend fun getStreamingData(@HeaderMap headers:Map<String, String>,
                                 @Query("groupId") groupId:String
                                 ):Streaming

}