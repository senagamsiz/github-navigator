package nl.abnamro.sena.assessment.data.repository

import nl.abnamro.sena.assessment.data.local.model.RepoItem
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun loadRepos()
    suspend fun showRepos(): Flow<List<RepoItem>>
}