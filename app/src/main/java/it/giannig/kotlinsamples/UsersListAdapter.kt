package it.giannig.kotlinsamples

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by giannig on 20/11/17.
 */

class UsersListAdapter(private var items: List<User>, private val onElementClick: (User, Context) -> Unit)
    : RecyclerView.Adapter<UsersListAdapter.UsersListAdapterViewHolder>() {

    companion object {
        const val TAG = "UsersListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UsersListAdapterViewHolder {
        val ctx = parent?.context
        val inflater = LayoutInflater.from(ctx)
        val v = inflater.inflate(R.layout.main_element, parent, false)

        return UsersListAdapterViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun switchList(newList : List<User>){
        this.items = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UsersListAdapterViewHolder?, position: Int) {
        val user = items[position]
        holder?.setText(user.name, user.surname)
        holder?.setImage(user.image)

        holder?.setOnClick { context ->
            Log.d(TAG, "received ${user.name}")
            onElementClick(user, context)
        }
    }

    class UsersListAdapterViewHolder(private val view: View) :
            RecyclerView.ViewHolder(view) {

        private val textName = view.findViewById<TextView>(R.id.element_text_name)!!
        private val textSurname = view.findViewById<TextView>(R.id.element_text_surname)!!
        private val userImage = view.findViewById<ImageView>(R.id.image_view_element)!!

        companion object {
            const val TAG = "MovieViewHolder"
        }

        fun setText(name: String, surname: String) {
            textName.text = name
            textSurname.text = surname
        }

        fun setOnClick(onClick: (Context) -> Unit) {
            view.setOnClickListener { onClick(view.context) }
        }

        fun setImage(imageUrl: String?) {
            Picasso.with(view.context)
                    .load(imageUrl)
                    .into(userImage)
        }
    }
}
