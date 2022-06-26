package nl.abnamro.sena.assessment.ui.viewmodel.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nl.abnamro.sena.assessment.data.repository.RepoRepository

class ReposOverviewViewModel(private val repository: RepoRepository) : ViewModel() {

    private val _uiState = MutableLiveData<ReposOverviewUiState>()
    val uiState: LiveData<ReposOverviewUiState>
        get() = _uiState

    fun dispatch(event: ReposOverviewEvent) {
        when (event) {
            is ReposOverviewEvent.LoadData -> loadReposData()
        }
    }

    private fun loadReposData() = viewModelScope.launch {
        _uiState.value = ReposOverviewUiState.ShowLoading
        try {
            repository.loadRepos()
        } catch (e: Exception) {
            Log.d("exception", e.message.toString())
        } finally {
            showAlreadyStoredRepos()
        }
    }

    private fun showAlreadyStoredRepos() = viewModelScope.launch {
        repository.showRepos().collect { repos ->
            if (repos.isNullOrEmpty()) {
                _uiState.value =
                    ReposOverviewUiState.ShowError("There is no any repo item to show!")
            } else {
                _uiState.value = ReposOverviewUiState.ReposLoaded(repos)
            }
        }
    }

}