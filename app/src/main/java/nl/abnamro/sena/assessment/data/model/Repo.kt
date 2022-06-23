package nl.abnamro.sena.assessment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


class Repo : ArrayList<Repo.RepoItem>(){

    @Entity(tableName = "repo_table")
    @Serializable
    data class RepoItem(
    //data class RepoItem(val data: @Contextual Any)
        @PrimaryKey
        @SerialName("id")
        @ColumnInfo(name = "id")
        val id: Int,

        @SerialName("node_id")
        @ColumnInfo(name = "node_id")
        val nodeId: String,

        @SerialName("name")
        @ColumnInfo(name = "name")
        val name: String,

        @SerialName("full_name")
        @ColumnInfo(name = "full_name")
        val fullName: String,

        @SerialName("private")
        @ColumnInfo(name = "private")
        val `private`: Boolean,

        @SerialName("owner")
        @ColumnInfo(name = "owner")
        val owner: Owner,

        @SerialName("html_url")
        @ColumnInfo(name = "html_url")
        val htmlUrl: String,

        @SerialName("description")
        @ColumnInfo(name = "description")
        val description: String,

        @SerialName("fork")
        @ColumnInfo(name = "fork")
        val fork: Boolean,

        @SerialName("url")
        @ColumnInfo(name = "url")
        val url: String,

        @SerialName("forks_url")
        @ColumnInfo(name = "forks_url")
        val forksUrl: String,

        @SerialName("keys_url")
        @ColumnInfo(name = "keys_url")
        val keysUrl: String,

        @SerialName("collaborators_url")
        @ColumnInfo(name = "collaborators_url")
        val collaboratorsUrl: String,

        @SerialName("teams_url")
        @ColumnInfo(name = "teams_url")
        val teamsUrl: String,

        @SerialName("hooks_url")
        @ColumnInfo(name = "hooks_url")
        val hooksUrl: String,

        @SerialName("issue_events_url")
        @ColumnInfo(name = "issue_events_url")
        val issueEventsUrl: String,

        @SerialName("events_url")
        @ColumnInfo(name = "events_url")
        val eventsUrl: String,

        @SerialName("assignees_url")
        @ColumnInfo(name = "assignees_url")
        val assigneesUrl: String,

        @SerialName("branches_url")
        @ColumnInfo(name = "branches_url")
        val branchesUrl: String,

        @SerialName("tags_url")
        @ColumnInfo(name = "tags_url")
        val tagsUrl: String,

        @SerialName("blobs_url")
        @ColumnInfo(name = "blobs_url")
        val blobsUrl: String,

        @SerialName("git_tags_url")
        @ColumnInfo(name = "git_tags_url")
        val gitTagsUrl: String,

        @SerialName("git_refs_url")
        @ColumnInfo(name = "git_refs_url")
        val gitRefsUrl: String,

        @SerialName("trees_url")
        @ColumnInfo(name = "trees_url")
        val treesUrl: String,

        @SerialName("statuses_url")
        @ColumnInfo(name = "statuses_url")
        val statusesUrl: String,

        @SerialName("languages_url")
        @ColumnInfo(name = "languages_url")
        val languagesUrl: String,

        @SerialName("stargazers_url")
        @ColumnInfo(name = "stargazers_url")
        val stargazersUrl: String,

        @SerialName("contributors_url")
        @ColumnInfo(name = "contributors_url")
        val contributorsUrl: String,

        @SerialName("subscribers_url")
        @ColumnInfo(name = "subscribers_url")
        val subscribersUrl: String,

        @SerialName("subscription_url")
        @ColumnInfo(name = "subscription_url")
        val subscriptionUrl: String,

        @SerialName("commits_url")
        @ColumnInfo(name = "commits_url")
        val commitsUrl: String,

        @SerialName("git_commits_url")
        @ColumnInfo(name = "git_commits_url")
        val gitCommitsUrl: String,

        @SerialName("comments_url")
        @ColumnInfo(name = "comments_url")
        val commentsUrl: String,

        @SerialName("issue_comment_url")
        @ColumnInfo(name = "issue_comment_url")
        val issueCommentUrl: String,

        @SerialName("contents_url")
        @ColumnInfo(name = "contents_url")
        val contentsUrl: String,

        @SerialName("compare_url")
        @ColumnInfo(name = "compare_url")
        val compareUrl: String,

        @SerialName("merges_url")
        @ColumnInfo(name = "merges_url")
        val mergesUrl: String,

        @SerialName("archive_url")
        @ColumnInfo(name = "archive_url")
        val archiveUrl: String,

        @SerialName("downloads_url")
        @ColumnInfo(name = "downloads_url")
        val downloadsUrl: String,

        @SerialName("issues_url")
        @ColumnInfo(name = "issues_url")
        val issuesUrl: String,

        @SerialName("pulls_url")
        @ColumnInfo(name = "pulls_url")
        val pullsUrl: String,

        @SerialName("milestones_url")
        @ColumnInfo(name = "milestones_url")
        val milestonesUrl: String,

        @SerialName("notifications_url")
        @ColumnInfo(name = "notifications_url")
        val notificationsUrl: String,

        @SerialName("labels_url")
        @ColumnInfo(name = "labels_url")
        val labelsUrl: String,

        @SerialName("releases_url")
        @ColumnInfo(name = "releases_url")
        val releasesUrl: String,

        @SerialName("deployments_url")
        @ColumnInfo(name = "deployments_url")
        val deploymentsUrl: String,

        @SerialName("created_at")
        @ColumnInfo(name = "created_at")
        val createdAt: String,

        @SerialName("updated_at")
        @ColumnInfo(name = "updated_at")
        val updatedAt: String,

        @SerialName("pushed_at")
        @ColumnInfo(name = "pushed_at")
        val pushedAt: String,

        @SerialName("git_url")
        @ColumnInfo(name = "git_url")
        val gitUrl: String,

        @SerialName("ssh_url")
        val sshUrl: String,

        @SerialName("clone_url")
        @ColumnInfo(name = "clone_url")
        val cloneUrl: String,

        @SerialName("svn_url")
        @ColumnInfo(name = "svn_url")
        val svnUrl: String,

        @SerialName("homepage")
        @ColumnInfo(name = "homepage")
        val homepage: String,

        @SerialName("size")
        @ColumnInfo(name = "size")
        val size: Int,

        @SerialName("stargazers_count")
        @ColumnInfo(name = "stargazers_count")
        val stargazersCount: Int,

        @SerialName("watchers_count")
        @ColumnInfo(name = "watchers_count")
        val watchersCount: Int,

        @SerialName("language")
        @ColumnInfo(name = "language")
        val language: String,

        @SerialName("has_issues")
        @ColumnInfo(name = "has_issues")
        val hasIssues: Boolean,

        @SerialName("has_projects")
        @ColumnInfo(name = "has_project")
        val hasProjects: Boolean,

        @SerialName("has_downloads")
        @ColumnInfo(name = "has_downloads")
        val hasDownloads: Boolean,

        @SerialName("has_wiki")
        @ColumnInfo(name = "has_wiki")
        val hasWiki: Boolean,

        @SerialName("has_pages")
        @ColumnInfo(name = "has_pages")
        val hasPages: Boolean,

        @SerialName("forks_count")
        @ColumnInfo(name = "forks_count")
        val forksCount: Int,

        @SerialName("mirror_url")
        @ColumnInfo(name = "mirror_url")
        val mirrorUrl: Any,

        @SerialName("archived")
        @ColumnInfo(name = "archived")
        val archived: Boolean,

        @SerialName("disabled")
        @ColumnInfo(name = "disabled")
        val disabled: Boolean,

        @SerialName("open_issues_count")
        @ColumnInfo(name = "open_issues_count")
        val openIssuesCount: Int,

        @SerialName("license")
        @ColumnInfo(name = "license")
        val license: License,

        @SerialName("allow_forking")
        @ColumnInfo(name = "allow_forking")
        val allowForking: Boolean,

        @SerialName("is_template")
        @ColumnInfo(name = "is_template")
        val isTemplate: Boolean,

        @SerialName("topics")
        @ColumnInfo(name = "topics")
         val  topics: List<Any>,

        @SerialName("visibility")
        @ColumnInfo(name = "visibility")
        val visibility: String,

        @SerialName("forks")
        @ColumnInfo(name = "forks")
        val forks: Int,

        @SerialName("open_issues")
        @ColumnInfo(name = "open_issues")
        val openIssues: Int,

        @SerialName("watchers")
        @ColumnInfo(name = "watchers")
        val watchers: Int,

        @SerialName("default_branch")
        @ColumnInfo(name = "default_branch")
        val defaultBranch: String
    ) {

        @Serializable
        data class Owner(

            @SerialName("login")
            @ColumnInfo(name = "login")
            val login: String,

            @SerialName("id")
            @ColumnInfo(name = "id")
            val id: Int,

            @SerialName("node_id")
            @ColumnInfo(name = "node_id")
            val nodeId: String,

            @SerialName("avatar_url")
            @ColumnInfo(name = "avatar_url")
            val avatarUrl: String,

            @SerialName("gravatar_id")
            @ColumnInfo(name = "gravatar_id")
            val gravatarId: String,

            @SerialName("url")
            @ColumnInfo(name = "url")
            val url: String,

            @SerialName("html_url")
            @ColumnInfo(name = "html_url")
            val htmlUrl: String,

            @SerialName("followers_url")
            @ColumnInfo(name = "followers_url")
            val followersUrl: String,

            @SerialName("following_url")
            @ColumnInfo(name = "following_url")
            val followingUrl: String,

            @SerialName("gists_url")
            @ColumnInfo(name = "gists_url")
            val gistsUrl: String,

            @SerialName("starred_url")
            @ColumnInfo(name = "starred_url")
            val starredUrl: String,

            @SerialName("subscriptions_url")
            @ColumnInfo(name = "subscriptions_url")
            val subscriptionsUrl: String,

            @SerialName("organizations_url")
            @ColumnInfo(name = "organizations_url")
            val organizationsUrl: String,

            @SerialName("repos_url")
            @ColumnInfo(name = "repos_url")
            val reposUrl: String,

            @SerialName("events_url")
            @ColumnInfo(name = "events_url")
            val eventsUrl: String,

            @SerialName("received_events_url")
            @ColumnInfo(name = "received_events_url")
            val receivedEventsUrl: String,

            @SerialName("type")
            @ColumnInfo(name = "type")
            val type: String,

            @SerialName("site_admin")
            @ColumnInfo(name = "site_admin")
            val siteAdmin: Boolean
        )

        @Serializable
        data class License(

            @SerialName("key")
            @ColumnInfo(name = "key")
            val key: String,

            @SerialName("name")
            @ColumnInfo(name = "name")
            val name: String,

            @SerialName("spdx_id")
            @ColumnInfo(name = "spdx_id")
            val spdxId: String,

            @SerialName("url")
            @ColumnInfo(name = "url")
            val url: String,

            @SerialName("node_id")
            @ColumnInfo(name = "node_id")
            val nodeId: String
        )
    }
}