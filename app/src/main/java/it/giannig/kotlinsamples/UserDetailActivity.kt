package it.giannig.kotlinsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.extras.get(EXTRA_USER) as User

        Picasso.with(this)
                .load(user.image)
                .into(user_image)

        user_name.text = user.name
    }
}
