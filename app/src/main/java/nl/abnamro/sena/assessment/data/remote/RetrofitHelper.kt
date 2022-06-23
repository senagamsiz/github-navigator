package nl.abnamro.sena.assessment.data.remote

import nl.abnamro.sena.assessment.data.remote.api.RepoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object RetrofitHelper {
    private val contentType: MediaType = MediaType.get("application/json")

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }




    fun provideHouseApi(): RepoApi =
        provideRetrofit().create(RepoApi::class.java)

}
