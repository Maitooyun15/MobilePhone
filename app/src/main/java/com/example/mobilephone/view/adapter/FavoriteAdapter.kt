package com.example.mobilephone.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.view.contract.FavoriteInterface
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    private val listener: FavoriteInterface.OnClickFavoriteList, private var shareFav: ModelPreferences
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    private var mobileList: ArrayList<MobileModel> = arrayListOf()

    fun favoriteList(list: ArrayList<MobileModel>) {
        mobileList = list
        notifyDataSetChanged()
    }

    fun addFavorite(fav: MobileModel) {
        mobileList.add(fav)
        notifyDataSetChanged()

    }

    fun removeAt(position: Int) {
        var removeFav = mobileList[position]
        mobileList.removeAt(position)
        shareFav.putObject("model", mobileList)
        listener.onSwipeRemove(removeFav)
        notifyDataSetChanged()
    }

    fun removeHeart(model: MobileModel) {
        mobileList.remove(model)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mobileList.count()
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mobileList[position], listener)
    }
}

class FavoriteViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)) {

    private val imageMobile: ImageView = itemView.findViewById(R.id.imagesMobile)
    private val txtName: TextView = itemView.findViewById(R.id.namesMobile)
    private val txtPrice: TextView = itemView.findViewById(R.id.priceMobile)
    private val txtRating: TextView = itemView.findViewById(R.id.ratingMobile)

    fun bind(model: MobileModel, listener: FavoriteInterface.OnClickFavoriteList) {

        Picasso.get()
            .load(model.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageMobile)
        txtName.text = model.id.toString()
        txtPrice.text = "${model.price}"
        txtRating.text = "Rating: ${model.rating}"

        itemView.setOnClickListener { listener.onFavoriteDetailClick(model) }
    }
}


