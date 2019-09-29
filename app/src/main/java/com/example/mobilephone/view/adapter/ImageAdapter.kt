package com.example.mobilephone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileImageModel
import com.squareup.picasso.Picasso

class ImageAdapter(private val mobileImage: List<MobileImageModel>) :
    RecyclerView.Adapter<MobileImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileImageViewHolder {
        return MobileImageViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mobileImage.count()
    }

    override fun onBindViewHolder(holder: MobileImageViewHolder, position: Int) {
        holder.bind(mobileImage[position])
    }
}

class MobileImageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
) {

    private val iImage: ImageView = itemView.findViewById(R.id.imageView)

    fun bind(model: MobileImageModel) {

        var image = model.url
        if(model.url.startsWith("www")){
            image = "https://$image"
        }

        Picasso.get()
            .load(image)
            .placeholder(R.mipmap.ic_launcher)
            .into(iImage)
    }

}