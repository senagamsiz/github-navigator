package nl.abnamro.sena.assessment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String,
    val avatarImageUrl: String,
    val isVisible: Boolean,
    val isPublic: Boolean,
    val repoUrl: String
)
