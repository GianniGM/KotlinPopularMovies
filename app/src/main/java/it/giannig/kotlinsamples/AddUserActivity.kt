package it.giannig.kotlinsamples

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_user.*
import java.io.Serializable

const val PHOTO_PICKER_ID = 1

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        person_image.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PHOTO_PICKER_ID)
        }

        submit_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_ADD_USER, User("ciccio") as Serializable)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHOTO_PICKER_ID) {
            Log.d("PICK_IMAGE", "resultCode: $resultCode, image picked: ${data?.data}")
            Picasso.with(this)
                    .load(data?.data)
                    .into(person_image)
        }
    }
}
