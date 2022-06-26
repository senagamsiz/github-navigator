package nl.abnamro.sena.assessment.ui.view.overview.adapter

import androidx.recyclerview.widget.DiffUtil
import nl.abnamro.sena.assessment.data.local.model.RepoItem

class RepoItemDiffCallBack : DiffUtil.ItemCallback<RepoItem>() {
    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem.id == newItem.id
    }
}