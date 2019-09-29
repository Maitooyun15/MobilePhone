package com.example.mobilephone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.view.contract.MobileInterface
import com.squareup.picasso.Picasso

class MobileAdapter(
    private val listener: MobileInterface.OnClickMobileList,
    private var shareFavorite: ModelPreferences?
) :
    RecyclerView.Adapter<MobileViewHolder>() {

    private var mobileList = listOf<MobileModel>()
    private var favList: ArrayList<MobileModel> = arrayListOf()

    init {
        shareFavorite?.getObject("model")?.let { favList.addAll(it) }
    }

    fun mobileList(list: List<MobileModel>) {
        mobileList = list
        notifyDataSetChanged()
    }

    fun updateFavorite(unFav: MobileModel) {
        favList.remove(unFav)
        mobileList.forEach {
            if (it.id == unFav.id) {
                it.checked = false
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(parent, shareFavorite, favList)
    }

    override fun getItemCount(): Int {
        return mobileList.count()
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        holder.bind(mobileList[position], listener)
    }

}

class MobileViewHolder(parent: ViewGroup, var shareFavorite: ModelPreferences?, var favList: ArrayList<MobileModel>) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_mobile, parent, false)
    ) {

    private val iImage: ImageView = itemView.findViewById(R.id.imagesMobile)
    private val txtName: TextView = itemView.findViewById(R.id.namesMobile)
    private val txtDescription: TextView = itemView.findViewById(R.id.priceMobile)
    private val txtPrice: TextView = itemView.findViewById(R.id.ratingMobile)
    private val txtRating: TextView = itemView.findViewById(R.id.rating)
    private val btnFavorite: ImageButton = itemView.findViewById(R.id.imageButton3)

    fun bind(model: MobileModel, listener: MobileInterface.OnClickMobileList) {

        Picasso.get()
            .load(model.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(iImage)
        txtName.text = model.id.toString()
        txtDescription.text = model.description
        txtPrice.text = "Price: $${model.price}"
        txtRating.text = "Rating: ${model.rating}"

        if (model.checked) {
            btnFavorite.setBackgroundResource(R.drawable.heartfull)
        } else {
            btnFavorite.setBackgroundResource(R.drawable.heart)
        }

        btnFavorite.setOnClickListener {
            if (model.checked) {
                listener.onRemoveHeart(model)
                favList.remove(model)
                shareFavorite?.putObject("model", favList)
                model.checked = false
                btnFavorite.setBackgroundResource(R.drawable.heart)

            } else {
                btnFavorite.setBackgroundResource(R.drawable.heartfull)
                model.checked = true
                favList.add(model)
                //  favList.toMutableSet()
                shareFavorite?.putObject("model", favList)
                listener.onFavoriteClick(model)

            }
        }

        itemView.setOnClickListener { listener.onMobileDetailClick(model) }
    }

}
