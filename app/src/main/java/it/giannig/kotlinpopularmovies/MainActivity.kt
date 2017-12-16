package it.giannig.kotlinpopularmovies

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
data class User(val name: String, val imageLink: String = "https://vignette.wikia.nocookie.net/fantendo/images/6/6e/Small-mario.png/revision/latest?cb=20120718024112")

class MainActivity : AppCompatActivity() {

    @JvmField val TAG = "MAIN_ACTIVITY"

    private val mockedData = arrayOf(
            User("Gianni"),
            User("Lorena"),
            User("Daniela"),
            User("Giorgio"),
            User("Nicola"),
            User("Davide")
    )

    private val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello_text_view.text = getString(R.string.message)

        val layoutManager = GridLayoutManager(this, calculateColumns())
        main_recycler_view.layoutManager = layoutManager
        main_recycler_view.adapter = MovieAdapter(
                mockedData,
                { counter:Int ->
                    Toast.makeText(ctx, "received $counter", Toast.LENGTH_SHORT).show()
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