package hu.jcp.androidchallange.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hu.jcp.androidchallange.R
import hu.jcp.androidchallange.util.Constant.IMAGE_STORAGE_BASE_URL

object ImageLoader {

    private val requestOption = RequestOptions()
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)

    fun loadImage(context: Context, image: String, targetView : ImageView) {
        Glide.with(context)
            .applyDefaultRequestOptions(requestOption)
            .load(IMAGE_STORAGE_BASE_URL.plus(image))
            .into(targetView)
    }
}