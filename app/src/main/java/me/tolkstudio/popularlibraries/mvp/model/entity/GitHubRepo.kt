package me.tolkstudio.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubRepo(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val forksCount: String
) : Parcelable