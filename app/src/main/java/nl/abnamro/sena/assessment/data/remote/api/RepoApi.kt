package nl.abnamro.sena.assessment.data.remote.api

import nl.abnamro.sena.assessment.data.model.Repo
import retrofit2.http.GET

interface RepoApi {
    @GET("repo")
    suspend fun getRepoFromApi(): List<Repo>
}