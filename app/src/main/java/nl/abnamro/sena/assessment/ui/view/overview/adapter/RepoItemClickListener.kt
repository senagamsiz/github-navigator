package nl.abnamro.sena.assessment.ui.view.overview.adapter

import nl.abnamro.sena.assessment.data.local.model.RepoItem

class RepoItemClickListener(val clickListener: (repoId: Int) -> Unit) {
    fun onClick(repo: RepoItem) = clickListener(repo.id)
}