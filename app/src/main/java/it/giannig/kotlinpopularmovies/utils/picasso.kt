package it.giannig.kotlinpopularmovies.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

/**
 * Created by giannig on 20/11/17.
 */

public val Context.picasso: Picasso
    get() = Picasso.with(this)

public fun ImageView.load(path: String, request: (RequestCreator) -> RequestCreator) {
    request(context.picasso.load(path)).into(this)
}
