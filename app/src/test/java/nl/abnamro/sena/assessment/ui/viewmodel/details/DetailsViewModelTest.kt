package nl.abnamro.sena.assessment.ui.viewmodel.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import nl.abnamro.sena.assessment.MainCoroutineRule
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.data.repository.RepoRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    private lateinit var detailsViewModel: RepoDetailsViewModel
    private val repository : RepoRepository = mock()
    private val uiStateObserver = mock<Observer<RepoDetailsUiState>>()
    private val navigationObserver = mock<Observer<RepoDetailsNavigationTarget>>()

    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        detailsViewModel = RepoDetailsViewModel(repository)
        detailsViewModel.uiState.observeForever(uiStateObserver)
        detailsViewModel.navigation.observeForever(navigationObserver)
    }

    @Test
    fun `verify repo detail is loaded for existing item`() = mainCoroutineRule.runBlockingTest {
        whenever(repository.getRepoById(REPO_ID)).thenReturn(flowItem)
        detailsViewModel.loadDetails(REPO_ID)
        verify(uiStateObserver).onChanged(RepoDetailsUiState.RepoDetails(repoItem))
    }

    @Test
    fun `verify open url event navigates correctly`() = mainCoroutineRule.runBlockingTest {
        detailsViewModel.dispatch(RepoDetailsEvent.RepoUrlButtonClick(URL))
        verify(navigationObserver).onChanged(RepoDetailsNavigationTarget.OpenRepoUrl(URL))
    }

    private val flowItem = flow { emit(repoItem) }

    private val repoItem = RepoItem(REPO_ID,
        "Name",
        "FullName",
        "Description",
        "Avatar",
        "public",
        true,
        "url")

    companion object {
        const val REPO_ID = 9312
        const val URL = "www.abnamro.nl"
    }
}


