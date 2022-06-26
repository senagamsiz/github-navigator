package nl.abnamro.sena.assessment.ui.viewmodel

sealed class ReposOverviewEvent {
    object LoadData: ReposOverviewEvent()
}