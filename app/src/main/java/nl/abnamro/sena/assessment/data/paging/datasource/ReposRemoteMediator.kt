package nl.abnamro.sena.assessment.data.paging.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import nl.abnamro.sena.assessment.data.local.database.AppDatabase
import nl.abnamro.sena.assessment.data.local.model.RemoteKeys
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.data.remote.api.RepoApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class ReposRemoteMediator(
    private val service: RepoApi,
    private val database: AppDatabase
) : RemoteMediator<Int, RepoItem>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoItem>
    ): MediatorResult {
        val page = when (loadType) {
            REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }
        try {
            val apiResponse = service.getReposFromApi(page, state.config.pageSize)
            val isEndOfPagination = apiResponse.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.repoDao().clearRepos()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfPagination) null else page + 1
                val keys = apiResponse.map {
                    RemoteKeys(repoId = it.id ?: 0, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAll(keys)
                val repos = apiResponse.map { repo ->
                    RepoItem(
                        id = repo.id ?: 0,
                        name = repo.name ?: "",
                        fullName = repo.fullName ?: "",
                        description = repo.description ?: "",
                        isPrivate = repo.isPrivate ?: false,
                        visibility = repo.visibility ?: "",
                        avatarImageUrl = repo.owner?.avatarUrl ?: "",
                        repoUrl = repo.htmlUrl ?: ""
                    )
                }.toTypedArray()

                database.repoDao().insertAllRepos(*repos)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfPagination)

        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RepoItem>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RepoItem>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                database.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RepoItem>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                database.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }
}