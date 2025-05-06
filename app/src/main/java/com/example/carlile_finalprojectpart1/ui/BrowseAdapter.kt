package com.example.carlile_finalprojectpart1.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.Comic


// This is an adapter used by BrowseFragment to bind data to the RecyclerView.
class BrowseAdapter(
    private val comics: List<Comic>,
    private val onFavoriteClick: (Comic) -> Unit
) : RecyclerView.Adapter<BrowseAdapter.ComicViewHolder>() {

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // define variables for each component in the item layout
        val comicImageView: ImageView = itemView.findViewById(R.id.iv_comic_item)
        val comicTitleTextView: TextView = itemView.findViewById(R.id.tv_comic_title_item)
        val favoriteButton: Button = itemView.findViewById(R.id.btn_favorite_item)
    }

    // inflate the item layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comic, parent, false)
        return ComicViewHolder(view)
    }

    // bind data to the ViewHolder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        // get the comic at the current position
        val comic = comics[position]

        // set the text and image of the ViewHolder
        holder.comicTitleTextView.text = "Comic #${comic.id} - ${comic.title}"

        // load the image using Glide
        Glide.with(holder.itemView.context)
            .load(comic.img)
            .placeholder(R.drawable.xkcd1_foreground)
            .into(holder.comicImageView)

        // set the click listener for the favorite button
        holder.favoriteButton.setOnClickListener {
            onFavoriteClick(comic)
        }
    }

    // get item count for recycler view since we only generate 10 items in Browse
    override fun getItemCount(): Int = comics.size
}


