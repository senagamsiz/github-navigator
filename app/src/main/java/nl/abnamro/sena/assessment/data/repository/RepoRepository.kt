package nl.abnamro.sena.assessment.data.repository

import kotlinx.coroutines.flow.Flow
import nl.abnamro.sena.assessment.data.local.model.RepoItem

interface RepoRepository {
    suspend fun loadRepos()
    suspend fun showRepos(): Flow<List<RepoItem>>
    suspend fun getRepoById(repoId: Int): Flow<RepoItem>
}