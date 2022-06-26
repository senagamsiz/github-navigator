package nl.abnamro.sena.assessment.ui.viewmodel

import nl.abnamro.sena.assessment.data.local.model.RepoItem

sealed class ReposOverviewUiState {
    data class ReposLoaded(val repos: List<RepoItem>) : ReposOverviewUiState()
    object ShowLoading: ReposOverviewUiState()
    object ShowError: ReposOverviewUiState()
}