package me.tolkstudio.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubRepo(
    @Expose val name: String,
    @Expose val avatarUrl: String,
    @Expose val reposUrl: String,
    @Expose val watchers: String,
    @Expose val forks: String,
    @Expose val defaultBranch: String
) : Parcelable