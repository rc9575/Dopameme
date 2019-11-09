package com.example.memes.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memes.Model.MemeData
import com.example.memes.R


class MainAdapter(var endpoints: List<MemeData>,
                  var clickListener: View.OnClickListener) :
    RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {


    override fun getItemCount(): Int {
        return endpoints.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val endpoint = endpoints[position]

        val imgUri = endpoint.url.toUri().buildUpon().scheme("https").build()

        Glide.with(holder.title.context)
            .load(imgUri)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.photo_item)
        holder.title.text =  endpoint.name

        val bundle = Bundle()
        bundle.putString("id", endpoint.id)
        holder.captionButton.setOnClickListener(Navigation.createNavigateOnClickListener(
            R.id.action_overviewFragment_to_captionFragment,
            bundle))

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false))
    }


    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                var photo_item: ImageView
                var title: TextView
                var captionButton : Button
                init {

                    itemView.setOnClickListener(clickListener)

                    itemView.tag = this
                    photo_item = itemView.findViewById(R.id.meme_image) as ImageView
                    title = itemView.findViewById(R.id.title) as TextView
                    captionButton = itemView.findViewById(R.id.button) as Button
                }
            }

}