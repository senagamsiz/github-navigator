package nl.abnamro.sena.assessment.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nl.abnamro.sena.assessment.data.local.model.RepoItem

interface RepoRepository {
    suspend fun getRepoById(repoId: Int): Flow<RepoItem>
    fun getRepos(pageSize: Int): Flow<PagingData<RepoItem>>
}