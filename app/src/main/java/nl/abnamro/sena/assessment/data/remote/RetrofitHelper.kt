package nl.abnamro.sena.assessment.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nl.abnamro.sena.assessment.BuildConfig
import nl.abnamro.sena.assessment.data.remote.api.RepoApi
import okhttp3.MediaType
import retrofit2.Retrofit

object RetrofitHelper {
    private val contentType: MediaType = MediaType.get("application/json")

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .addConverterFactory(
                json.asConverterFactory(contentType)
            ).build()
    }

    fun provideRepoApi(): RepoApi =
        provideRetrofit().create(RepoApi::class.java)

}
