package nl.abnamro.sena.assessment.data.remote.api

import nl.abnamro.sena.assessment.data.remote.model.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {
    @GET("repos?")
    suspend fun getReposFromApi(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<Repo>
}