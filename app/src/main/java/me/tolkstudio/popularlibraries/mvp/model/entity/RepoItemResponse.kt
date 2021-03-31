package me.tolkstudio.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class RepoItemResponse(
    @Expose val fork: String
) : Parcelable