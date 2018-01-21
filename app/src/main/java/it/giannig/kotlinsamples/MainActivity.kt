package it.giannig.kotlinsamples

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


data class User(
        val name: String = "",
        val surname: String = "",
        var image: String?
) : Serializable

const val EXTRA_USER = "image_url_data"
const val EXTRA_ADD_USER = "add_user_data"
const val ADD_USER_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {

    private val ctx = this
    private val data: ArrayList<User> = ArrayList()

    companion object {
        const val TAG = "MAIN_ACTIVITY"
    }

    //todo: da sostituire con la API
//    private val mockedData = arrayOf(
//            User("Gianni"),
//            User("Lorena"),
//            User("Daniela"),
//            User("Giorgio", "http://crazyforclashofclans.altervista.org/wp-content/uploads/2015/08/250px-Builder.png"),
//            User("Nicola"),
//            User("Davide"),
//            User("Polacco", "https://ih1.redbubble.net/image.176691509.2899/sticker,375x360-bg,ffffff.u1.png"),
//            User("Alessio"),
//            User("Annalina"),
//            User("Ilaria")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = GridLayoutManager(this, calculateColumns())
        main_recycler_view.layoutManager = layoutManager
        main_recycler_view.adapter = MovieAdapter(data) { user, _ -> this.onClick(user) }
        fab.setOnClickListener { this.onFabClick() }
    }

    private fun onClick(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(EXTRA_USER, user as Serializable)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "request code: $requestCode, resultCode: $resultCode/${data?.extras?.get(EXTRA_ADD_USER)}")

        when (requestCode) {
            ADD_USER_REQUEST_CODE -> when (resultCode) {
                Activity.RESULT_OK -> {
                    val newUser = data?.extras?.get(EXTRA_ADD_USER) as User
                    this.data.add(newUser)
                    Toast.makeText(this,
                            "we have new user here ${newUser.name}",
                            Toast.LENGTH_LONG
                    ).show()
                }
                Activity.RESULT_CANCELED -> Toast.makeText(this,
                        "Aborted, changed will not save",
                        Toast.LENGTH_SHORT).show()
                else -> Log.e(TAG, "sometimes strange appened!")
            }
            else -> Log.e(TAG, "request code is not exists")
        }
    }

    private fun onFabClick() {
        val intent = Intent(this, AddUserActivity::class.java)
        startActivityForResult(intent, ADD_USER_REQUEST_CODE)
    }

    private fun calculateColumns(): Int {
        val displayMetrics = ctx.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val nColumns = (dpWidth / 180).toInt()

        Log.d(TAG, nColumns.toString())
        return nColumns
    }

}