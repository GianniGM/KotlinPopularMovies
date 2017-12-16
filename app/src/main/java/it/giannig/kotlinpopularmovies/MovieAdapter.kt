package it.giannig.kotlinpopularmovies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Created by giannig on 20/11/17.
 */

class MovieAdapter(private val items: Array<User>, private val onMovieClick: (Int) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {

    @JvmField val TAG = "MovieAdapter"

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
        val user =items[position]
        holder?.setTextElement(user.name)
        holder?.setImage(user.url)
    }

    class MovieAdapterViewHolder(private val view: View):
            RecyclerView.ViewHolder(view){

        @JvmField val TAG = "MovieViewHolder"

        private fun onClick(message: String){
            Log.d(TAG,"received $message")
            Toast.makeText(
                    view.context,
                    "received $message",
                    Toast.LENGTH_SHORT
            ).show()
        }

        fun setTextElement(message: String) {
            val text = view.findViewById<TextView>(R.id.element_text)
            text.text = message
            view.setOnClickListener { onClick(message) }
        }

        fun setImage(imageUrl: String){
            val imageView = view.findViewById<ImageView>(R.id.image_view_element)

            Picasso.with(view.context)
                    .load(imageUrl)
                    .into(imageView)
        }
    }
}
