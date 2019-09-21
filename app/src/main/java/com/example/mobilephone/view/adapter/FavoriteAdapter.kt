package com.example.mobilephone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    private val listener: OnMobileClickListener,
    private val modelPreferences: ModelPreferences?
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    private var mobileList: ArrayList<MobileModel> = arrayListOf()

    fun addFavorite(list: ArrayList<MobileModel>) {
        this.mobileList = list
        this.notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        mobileList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(parent, modelPreferences)
    }

    override fun getItemCount(): Int {
        return mobileList.count()
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mobileList[position], listener)
    }

}


class FavoriteViewHolder(parent: ViewGroup, var mobile: ModelPreferences?) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)) {


    private val imageMobile: ImageView = itemView.findViewById(R.id.imagesMobile)
    private val txtName: TextView = itemView.findViewById(R.id.namesMobile)
    private val txtPrice: TextView = itemView.findViewById(R.id.priceMobile)
    private val txtRating: TextView = itemView.findViewById(R.id.ratingMobile)


    fun bind(model: MobileModel, listener: OnMobileClickListener) {


        Picasso.get()
            .load(model.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageMobile)
        txtName.text = model.name
        txtPrice.text = "${model.price}"
        txtRating.text = "Rating: ${model.rating}"

        // mobile?.getObject("1" , MobileModel::class.java)


        itemView.setOnClickListener { listener.onMobileClick(model) }

    }
}

