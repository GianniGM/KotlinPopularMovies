package it.giannig.kotlinpopularmovies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


data class User(
        val name: String,
        val url: String = "http://www.ristorantepaguro.it/images/centrino.jpg"
) : Serializable

val EXTRA_USER = "image_url_data"
val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {

    private val ctx = this

    //todo: da sostituire con la API
    private val mockedData = arrayOf(
            User("Gianni"),
            User("Lorena"),
            User("Daniela"),
            User("Giorgio", "http://crazyforclashofclans.altervista.org/wp-content/uploads/2015/08/250px-Builder.png"),
            User("Nicola"),
            User("Davide"),
            User("Polacco", "https://ih1.redbubble.net/image.176691509.2899/sticker,375x360-bg,ffffff.u1.png"),
            User("Alessio"),
            User("Annalina"),
            User("Ilaria")
    )

    private val onClick: (User, Context) -> Unit = {
        user, context ->
        Toast.makeText(
                context,
                "opening ${user.name} card...",
                Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(EXTRA_USER, user as Serializable)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = GridLayoutManager(this, calculateColumns())
        main_recycler_view.layoutManager = layoutManager
        main_recycler_view.adapter = MovieAdapter(mockedData, this.onClick)
    }

    private fun calculateColumns(): Int {
        val displayMetrics = ctx.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val nColumns = (dpWidth / 180).toInt()

        Log.d(TAG, nColumns.toString())
        return nColumns
    }

}