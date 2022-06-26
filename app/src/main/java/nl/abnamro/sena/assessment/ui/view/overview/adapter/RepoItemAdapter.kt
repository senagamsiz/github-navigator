package nl.abnamro.sena.assessment.ui.view.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.databinding.RepoItemBinding


class RepoItemAdapter(private val clickListener: RepoItemClickListener) :
    ListAdapter<RepoItem, RepoItemAdapter.ViewHolder>(RepoItemDiffCallBack()) {

    class ViewHolder private constructor(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: RepoItem, clickListener: RepoItemClickListener) {
            Glide.with(binding.root.context).load(item.avatarImageUrl)
                .into(binding.ownerAvatarImage)
            binding.nameText.text = item.name
            binding.visibilityText.text = item.visibility
            binding.privateOrPublicText.text = if (item.isPrivate) "Private" else "Public"
            binding.root.setOnClickListener { clickListener.clickListener(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

}