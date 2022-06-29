package nl.abnamro.sena.assessment.ui.viewmodel.overview

sealed class ReposOverviewEvent {
    object LoadData: ReposOverviewEvent()
}