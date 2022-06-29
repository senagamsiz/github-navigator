package nl.abnamro.sena.assessment.ui.viewmodel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nl.abnamro.sena.assessment.data.repository.RepoRepository

class RepoDetailsViewModel(private val repository: RepoRepository) : ViewModel() {

    private val _uiState = MutableLiveData<RepoDetailsUiState>()
    val uiState: LiveData<RepoDetailsUiState>
        get() = _uiState

    private val _navigation = MutableLiveData<RepoDetailsNavigationTarget>()
    val navigation: LiveData<RepoDetailsNavigationTarget>
        get() = _navigation

    fun dispatch(event: RepoDetailsEvent) {
        when (event) {
           is RepoDetailsEvent.RepoUrlButtonClick -> _navigation.value = RepoDetailsNavigationTarget.OpenRepoUrl(event.url)
        }
    }

    fun loadDetails(id: Int) = viewModelScope.launch {
        repository.getRepoById(id).collect { repoItem ->
            _uiState.value = RepoDetailsUiState.RepoDetails(repoItem)
        }
    }
}