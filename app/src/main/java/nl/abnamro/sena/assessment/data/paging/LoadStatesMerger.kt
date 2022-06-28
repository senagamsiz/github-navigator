package nl.abnamro.sena.assessment.data.paging

import androidx.paging.CombinedLoadStates
import androidx.paging.*
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadState.Loading
import androidx.paging.PagingSource.LoadResult.Error
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.scan
import androidx.paging.LoadType.REFRESH

@OptIn(ExperimentalCoroutinesApi::class)
fun Flow<CombinedLoadStates>.asMergedLoadStates(): Flow<LoadStates> {
    val syncRemoteState = LoadStatesMerger()
    return scan(syncRemoteState.toLoadStates()) { _, combinedLoadStates ->
        syncRemoteState.updateFromCombinedLoadStates(combinedLoadStates)
        syncRemoteState.toLoadStates()
    }
}

private class LoadStatesMerger {
    var refresh: LoadState = NotLoading(endOfPaginationReached = false)
        private set
    var prepend: LoadState = NotLoading(endOfPaginationReached = false)
        private set
    var append: LoadState = NotLoading(endOfPaginationReached = false)
        private set
    var refreshState: MergedState = MergedState.NOT_LOADING
        private set
    var prependState: MergedState = MergedState.NOT_LOADING
        private set
    var appendState: MergedState = MergedState.NOT_LOADING
        private set

    fun toLoadStates() = LoadStates(
        refresh = refresh,
        prepend = prepend,
        append = append
    )

    fun updateFromCombinedLoadStates(combinedLoadStates: CombinedLoadStates) {
        computeNextLoadStateAndMergedState(
            sourceRefreshState = combinedLoadStates.source.refresh,
            sourceState = combinedLoadStates.source.refresh,
            remoteState = combinedLoadStates.mediator?.refresh,
            currentMergedState = refreshState,
        ).also {
            refresh = it.first
            refreshState = it.second
        }
        computeNextLoadStateAndMergedState(
            sourceRefreshState = combinedLoadStates.source.refresh,
            sourceState = combinedLoadStates.source.prepend,
            remoteState = combinedLoadStates.mediator?.prepend,
            currentMergedState = prependState,
        ).also {
            prepend = it.first
            prependState = it.second
        }
        computeNextLoadStateAndMergedState(
            sourceRefreshState = combinedLoadStates.source.refresh,
            sourceState = combinedLoadStates.source.append,
            remoteState = combinedLoadStates.mediator?.append,
            currentMergedState = appendState,
        ).also {
            append = it.first
            appendState = it.second
        }
    }

    private fun computeNextLoadStateAndMergedState(
        sourceRefreshState: LoadState,
        sourceState: LoadState,
        remoteState: LoadState?,
        currentMergedState: MergedState,
    ): Pair<LoadState, MergedState> {
        if (remoteState == null) return sourceState to MergedState.NOT_LOADING

        return when (currentMergedState) {
            MergedState.NOT_LOADING -> when (remoteState) {
                is Loading -> Loading to MergedState.REMOTE_STARTED
                is Error<*, *> -> remoteState to MergedState.REMOTE_ERROR
                else -> NotLoading(remoteState.endOfPaginationReached) to MergedState.NOT_LOADING
            }
            MergedState.REMOTE_STARTED -> when {
                remoteState is Error<*, *> -> remoteState to MergedState.REMOTE_ERROR
                sourceRefreshState is Loading -> Loading to MergedState.SOURCE_LOADING
                else -> Loading to MergedState.REMOTE_STARTED
            }
            MergedState.REMOTE_ERROR -> when (remoteState) {
                is Error<*, *> -> remoteState to MergedState.REMOTE_ERROR
                else -> Loading to MergedState.REMOTE_STARTED
            }
            MergedState.SOURCE_LOADING -> when {
                sourceRefreshState is Error<*, *> -> sourceRefreshState to MergedState.SOURCE_ERROR
                remoteState is Error<*, *> -> remoteState to MergedState.REMOTE_ERROR
                sourceRefreshState is NotLoading -> {
                    NotLoading(remoteState.endOfPaginationReached) to MergedState.NOT_LOADING
                }
                else -> Loading to MergedState.SOURCE_LOADING
            }
            MergedState.SOURCE_ERROR -> when (sourceRefreshState) {
                is Error<*, *> -> sourceRefreshState to MergedState.SOURCE_ERROR
                else -> sourceRefreshState to MergedState.SOURCE_LOADING
            }
        }
    }
}

private enum class MergedState {
    NOT_LOADING,
    REMOTE_STARTED,
    REMOTE_ERROR,
    SOURCE_LOADING,
    SOURCE_ERROR,
}