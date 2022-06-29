package nl.abnamro.sena.assessment.ui.viewmodel.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import nl.abnamro.sena.assessment.data.repository.RepoRepository

class ReposOverviewViewModel(repository: RepoRepository) : ViewModel() {

    val repos = repository
        .getRepos(5)
        .cachedIn(viewModelScope)

}