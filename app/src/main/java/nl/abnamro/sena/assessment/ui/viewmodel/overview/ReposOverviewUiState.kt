package nl.abnamro.sena.assessment.ui.viewmodel.overview

import nl.abnamro.sena.assessment.data.local.model.RepoItem

sealed class ReposOverviewUiState {
    data class ReposLoaded(val repos: List<RepoItem>) : ReposOverviewUiState()
    data class ShowError(val message: String) : ReposOverviewUiState()
    object ShowLoading : ReposOverviewUiState()
}