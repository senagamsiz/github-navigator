package nl.abnamro.sena.assessment.data.remote.api

import nl.abnamro.sena.assessment.data.remote.model.Repo
import retrofit2.http.GET

interface RepoApi {
    @GET
    suspend fun getReposFromApi(): List<Repo>
}