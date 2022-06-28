package nl.abnamro.sena.assessment.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import nl.abnamro.sena.assessment.data.local.database.AppDatabase
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.data.paging.datasource.ReposRemoteMediator
import nl.abnamro.sena.assessment.data.remote.api.RepoApi


class RepoRepositoryImpl(
    private val repoDatabase: AppDatabase,
    private val repoApi: RepoApi
) : RepoRepository {

    override suspend fun getRepoById(repoId: Int): Flow<RepoItem> {
        return repoDatabase.repoDao().getRepoById(repoId)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getRepos(pageSize: Int) = Pager(
        config = PagingConfig(pageSize), remoteMediator = ReposRemoteMediator(repoApi, repoDatabase)
    ){
        repoDatabase.repoDao().getAllRepos()
    }.flow
}