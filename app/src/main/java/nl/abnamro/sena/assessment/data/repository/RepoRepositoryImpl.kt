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
            val repoList: Array<RepoItem> = repoApi.getReposFromApi().map { repo ->
                RepoItem(
                    id = repo.id,
                    name = repo.name,
                    fullName = repo.fullName,
                    description = repo.description,
                    isPrivate = repo.isPrivate,
                    visibility = repo.visibility,
                    avatarImageUrl = repo.owner.avatarUrl,
                    repoUrl = repo.htmlUrl
                )
            }.toTypedArray()
            repoDao.insertAllRepos(*repoList)
        }
    }

    override suspend fun showRepos(): Flow<List<RepoItem>> {
        return repoDao.getAllRepos()
    }
}