package nl.abnamro.sena.assessment.ui.viewmodel.details

sealed class RepoDetailsNavigationTarget{
    data class OpenRepoUrl(val url: String): RepoDetailsNavigationTarget()
}
