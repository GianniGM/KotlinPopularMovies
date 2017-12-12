package it.giannig.kotlinpopularmovies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

/**
 * Created by giannig on 20/11/17.
 */

class MovieAdapter(private val items: Array<String>, private val onMovieClick: (Int) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {

    @JvmField val TAG = "MovieAdapter"

    var currentValue = ""

//    init {
//        Log.d(TAG, "STARTED MOVIE ADAPTER")
//    }
//    COSTRUTTORE INUTILE, SERVE SOLTANTO A FAR CAPRIE COME SI AGGIUNGE IL SECONDO COSTRUTTORE
//    constructor(items: Array<String>, ctx: Context, intero: Int) : this(items, ctx) {
//        Log.d(TAG, "QUESTO COSTRUTTORE É INUTILE")
//    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieAdapterViewHolder {
        val ctx = parent?.context
        val inflater = LayoutInflater.from(ctx)
        val v = inflater.inflate(R.layout.main_element, parent, false)

        return MovieAdapterViewHolder(v, onMovieClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder?, position: Int) {
        currentValue = items[position]
        holder?.setTextElement(currentValue)
    }

    class MovieAdapterViewHolder(private var view: View, private var onMovieClick: (Int) -> Unit) :
            RecyclerView.ViewHolder(view),
            View.OnClickListener {

        override fun onClick(p0: View?) {
            Log.d("ON_CLICK", "CLIKED")
            onMovieClick(adapterPosition)
        }

        fun setTextElement(message: String) {
            val text = view.findViewById<TextView>(R.id.element_text)
            text.text = message
        }
    }
}
