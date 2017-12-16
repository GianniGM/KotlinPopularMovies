package it.giannig.kotlinpopularmovies

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_user.*
import java.io.Serializable
import kotlinx.android.synthetic.main.activity_user_detail.*

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        submit_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_ADD_USER, User("ciccio") as Serializable)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
