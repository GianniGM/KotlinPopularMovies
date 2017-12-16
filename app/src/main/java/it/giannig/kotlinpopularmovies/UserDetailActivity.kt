package it.giannig.kotlinpopularmovies
import kotlinx.android.synthetic.main.activity_user_detail.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.extras.get(EXTRA_USER) as User
        Toast.makeText(this, "l'utente ricevuto è ${user.name}", Toast.LENGTH_SHORT).show()
    }
}
