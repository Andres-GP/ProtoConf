package com.protompany.protoconf.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.protompany.protoconf.R
import com.protompany.protoconf.model.Author
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AuthorsAdapter(val authorsListener: AuthorsListener) : RecyclerView.Adapter<AuthorsAdapter.ViewHolder>() {

    var listAuthors = ArrayList<Author>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_author, parent, false))

    override fun getItemCount() = listAuthors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val author = listAuthors[position] as Author
        holder.tvItemAuthorName.text = author.name
        holder.tvItemAuthorOccupation.text = author.occupation

        Glide.with(holder.itemView.context)
                .load(author.image)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivItemImage)

        holder.itemView.setOnClickListener {
            authorsListener.onAuthorsClicked(author, position)
        }
    }

    fun updateData(data: List<Author>){
        listAuthors.clear()
        listAuthors.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage = itemView.findViewById<ImageView>(R.id.ivItemAuthorImage)
        val tvItemAuthorName = itemView.findViewById<TextView>(R.id.tvItemAuthorName)
        val tvItemAuthorOccupation = itemView.findViewById<TextView>(R.id.tvItemAuthorOccupation)

    }

}




