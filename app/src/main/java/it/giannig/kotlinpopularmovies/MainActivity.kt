package it.giannig.kotlinpopularmovies

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


data class User(
        val name: String,
        val url: String = "https://upload.wikimedia.org/wikipedia/commons/b/b4/JPEG_example_JPG_RIP_100.jpg"
)

class MainActivity : AppCompatActivity() {

    @JvmField val TAG = "MAIN_ACTIVITY"

    private val mockedData = arrayOf(
            User("Gianni"),
            User("Lorena"),
            User("Daniela"),
            User("Giorgio", "http://i.imgur.com/DvpvklR.png"),
            User("Nicola"),
            User("Davide")
    )

    private val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = GridLayoutManager(this, calculateColumns())
        main_recycler_view.layoutManager = layoutManager
        main_recycler_view.adapter = MovieAdapter(
                mockedData,
                { counter:Int ->
                    Toast.makeText(
                            ctx,
                            "received $counter",
                            Toast.LENGTH_SHORT
                    ).show()
                }
        )
    }

    private fun calculateColumns(): Int {
        val displayMetrics = ctx.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val nColumns = (dpWidth / 180).toInt()

        Log.d(TAG, nColumns.toString())
        return nColumns
    }

}