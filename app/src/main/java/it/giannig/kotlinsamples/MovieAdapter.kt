package it.giannig.kotlinsamples

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by giannig on 20/11/17.
 */

class MovieAdapter(private val items: Array<User>, private val onElementClick: (User, Context) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {

    companion object {
        const val TAG = "MovieAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieAdapterViewHolder {
        val ctx = parent?.context
        val inflater = LayoutInflater.from(ctx)
        val v = inflater.inflate(R.layout.main_element, parent, false)

        return MovieAdapterViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder?, position: Int) {
        val user = items[position]
        holder?.setText(user.name)
        holder?.setImage(user.url)

        holder?.setOnClick { context ->
            Log.d(TAG, "received ${user.name}")
            onElementClick(user, context)
        }
    }

    class MovieAdapterViewHolder(private val view: View) :
            RecyclerView.ViewHolder(view) {

        companion object {
            const val TAG = "MovieViewHolder"
        }

        fun setText(message: String) {
            val text = view.findViewById<TextView>(R.id.element_text)
            text.text = message
        }

        fun setOnClick(onClick: (Context) -> Unit) {
            view.setOnClickListener { onClick(view.context) }
        }

        fun setImage(imageUrl: String) {
            val imageView = view.findViewById<ImageView>(R.id.image_view_element)

            Picasso.with(view.context)
                    .load(imageUrl)
                    .into(imageView)
        }
    }
}
