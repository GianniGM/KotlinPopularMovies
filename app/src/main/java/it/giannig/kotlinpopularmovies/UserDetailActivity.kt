package it.giannig.kotlinpopularmovies
import android.app.Activity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_user_detail.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.extras.get(EXTRA_USER) as User

        Picasso.with(this)
                .load(user.url)
                .into(user_image)

        user_name.text = user.name
    }
}
