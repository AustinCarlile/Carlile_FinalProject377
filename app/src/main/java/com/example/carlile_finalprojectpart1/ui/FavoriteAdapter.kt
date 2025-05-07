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

// This is an adapter used by FavoritesFragment to bind data to the RecyclerView.
class FavoritesAdapter(
    private val favorites: List<Comic>,
    private val onUnfavoriteClick: (Comic) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteComicViewHolder>() {

    // ViewHolder for the favorite comic item
    class FavoriteComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comicImageView: ImageView = itemView.findViewById(R.id.iv_comic_item)
        val comicTitleTextView: TextView = itemView.findViewById(R.id.tv_comic_title_item)
        val unfavoriteButton: Button = itemView.findViewById(R.id.btn_unfavorite_item)
    }

    // inflate the item layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_comic, parent, false)
        return FavoriteComicViewHolder(view)
    }

    // bind data to the ViewHolder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavoriteComicViewHolder, position: Int) {
        val comic = favorites[position]

        // set the text and image of the ViewHolder
        holder.comicTitleTextView.text = "Comic #${comic.id} - ${comic.title}"

        // load the image using Glide
        Glide.with(holder.itemView.context)
            .load(comic.img)
            .placeholder(R.drawable.xkcd1_foreground)
            .into(holder.comicImageView)

        // set the click listener for unfavoriting the comic
        holder.unfavoriteButton.setOnClickListener {
            onUnfavoriteClick(comic)
        }
    }

    // get item count for recycler view
    override fun getItemCount(): Int = favorites.size
}


