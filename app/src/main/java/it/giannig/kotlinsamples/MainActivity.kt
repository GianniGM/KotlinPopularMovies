package it.giannig.kotlinsamples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import kotlin.properties.Delegates.observable


const val EXTRA_USER = "image_url_data"
const val EXTRA_ADD_USER = "add_user_data"
const val ADD_USER_REQUEST_CODE = 0
const val URL_BASE_IMAGE = "https://ih1.redbubble.net/image.176691509.2899/sticker,375x360-bg,ffffff.u1.png"

data class User(
        val name: String = "",
        val surname: String = "",
        var image: String? = URL_BASE_IMAGE
) : Serializable

class MainActivity : AppCompatActivity() {

    private val ctx = this
    private lateinit var movieAdapter: UsersListAdapter
    private var data: List<User> by observable(listOf()) { _, _, new ->
        this.movieAdapter.switchList(new)
    }

    companion object {
        const val TAG = "MAIN_ACTIVITY"
    }

//    fun ArrayList<User>.addUser(u : User) : Observable<ArrayList<User>> {
//        this.add(u)
//        return Observable.just(this)
//    }
//
//    private var newUser:User by Delegates.observable(User()) {
//        _, _, new -> data.add(new)
//    }

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
        this.movieAdapter = UsersListAdapter(data) { user, _ -> this.onClick(user) }
        main_recycler_view.adapter = this.movieAdapter
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
            ADD_USER_REQUEST_CODE -> addUserRequestCode(resultCode, data)
            else -> Log.e(TAG, "request code is not exists")
        }
    }

    private fun addUserRequestCode(resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_OK -> {
                val newUser = data?.extras?.get(EXTRA_ADD_USER) as User
                this.data= this.data.plus(newUser)
                showToast("we have new user here ${newUser.name}")
            }
            Activity.RESULT_CANCELED -> {
                showToast("Aborted, changed will not save")
            }
            else -> Log.e(TAG, "sometimes strange appened!")
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(this,
                string,
                Toast.LENGTH_LONG
        ).show()
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