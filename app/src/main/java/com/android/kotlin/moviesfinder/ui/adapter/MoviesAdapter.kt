package com.android.kotlin.moviesfinder.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlin.dependencyinjectionhilt.R
import com.android.kotlin.moviesfinder.network.dto.MovieDto
import com.bumptech.glide.Glide

class MoviesAdapter(private var dataSet: List<MovieDto>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val year: TextView
        val poster: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.movieTitle)
            year = view.findViewById(R.id.movieYear)
            poster = view.findViewById(R.id.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = View.inflate(parent.context, R.layout.movie_row, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = dataSet[position]
        holder.name.text = movie.title
        holder.year.text = movie.year
        Glide.with(holder.itemView.context).load(movie.poster)
            .into(holder.poster)
    }

    fun update(dataSet: List<MovieDto>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}