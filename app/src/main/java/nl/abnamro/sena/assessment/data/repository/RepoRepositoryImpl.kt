package nl.abnamro.sena.assessment.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import nl.abnamro.sena.assessment.data.local.dao.RepoDao
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.data.remote.api.RepoApi

class RepoRepositoryImpl(
    private val repoDao: RepoDao,
    private val repoApi: RepoApi
) : RepoRepository {

    override suspend fun loadRepos() {
        withContext(Dispatchers.IO) {
            val repoList = repoApi.getReposFromApi().toTypedArray()
            repoDao.insertAllRepos(*)
        }
    }

    override suspend fun showRepos(): Flow<List<RepoItem>> {
        return repoDao.getAllRepos()
    }
}