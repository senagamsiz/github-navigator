package nl.abnamro.sena.assessment.ui.viewmodel.details

sealed class RepoDetailsEvent {
    data class RepoUrlButtonClick(val url: String) : RepoDetailsEvent()
}
