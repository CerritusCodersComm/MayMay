package com.example.maymay

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MemeAdapter(private val context: Context) :
    RecyclerView.Adapter<MemeAdapter.ViewHolder>() {

    private lateinit var memeModel: MemeModel
    private var memes: List<MemeModel.Meme> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meme_template_item, parent, false)

        return ViewHolder(
            view
        )
    }

    fun setData(memeModel: MemeModel) {
        this.memeModel = memeModel
        memes = memeModel.data.memes
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val meme = memes[position]

        // sets the title of a question to the textView from our itemHolder class
        holder.memeId.text = meme.id

        holder.memeName.text = meme.name

        Glide.with(context)
            .load(meme.url)
            .error(R.drawable.ic_launcher_background)
            .into(holder.memeImage)
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return memes.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val memeImage: ImageView = itemView.findViewById(R.id.meme_image)
        val memeId: TextView = itemView.findViewById(R.id.meme_id)
        val memeName: TextView = itemView.findViewById(R.id.meme_name)
    }
}

