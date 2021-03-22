package me.tolkstudio.popularlibraries.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import me.tolkstudio.popularlibraries.mvp.model.image.IImageLoader

class GlideImageLoader: IImageLoader<ImageView> {
    override fun load(url: String, container: ImageView) {

        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}