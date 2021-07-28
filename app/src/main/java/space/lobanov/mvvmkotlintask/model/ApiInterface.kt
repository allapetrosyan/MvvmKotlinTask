package space.lobanov.mvvmkotlintask.model

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("https://pixabay.com/api/?key=22267756-c94bb4f4cab58f572181f64e1&q=yellow+flowers&image_type=photo")
    suspend fun getHitData(): Response<HitData>
}