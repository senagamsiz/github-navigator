package nl.abnamro.sena.assessment.ui.viewmodel.details

import nl.abnamro.sena.assessment.data.local.model.RepoItem

sealed class RepoDetailsUiState {
    data class RepoDetails(val repoItem: RepoItem) : RepoDetailsUiState()
}
