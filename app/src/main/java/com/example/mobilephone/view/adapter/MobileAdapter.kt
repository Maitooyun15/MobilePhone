package com.example.mobilephone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.squareup.picasso.Picasso

class MobileAdapter(private val listener: OnMobileClickListener, private val mobile: ModelPreferences?) :
    RecyclerView.Adapter<MobileViewHolder>() {

    private var mobileList = listOf<MobileModel>()

    fun addMobile(list: List<MobileModel>) {
        this.mobileList = list
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(parent, mobile)
    }

    override fun getItemCount(): Int {
        return mobileList.count()
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        holder.bind(mobileList[position], listener)

    }

}

class MobileViewHolder(parent: ViewGroup, var mobile: ModelPreferences?) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_mobile, parent, false)
) {

    private val iImage: ImageView = itemView.findViewById(R.id.imagesMobile)
    private val txtName: TextView = itemView.findViewById(R.id.namesMobile)
    private val txtDescription: TextView = itemView.findViewById(R.id.priceMobile)
    private val txtPrice: TextView = itemView.findViewById(R.id.ratingMobile)
    private val txtRating: TextView = itemView.findViewById(R.id.rating)
    private val btnFavorite: ImageButton = itemView.findViewById(R.id.imageButton3)


    fun bind(model: MobileModel, listener: OnMobileClickListener) {


        Picasso.get()
            .load(model.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(iImage)
        txtName.text = model.name
        txtDescription.text = model.description
        txtPrice.text = "Price: $${model.price}"
        txtRating.text = "Rating: ${model.rating}"


        var check = model.chacked


        btnFavorite.setOnClickListener {

            check = if (!check) {
                btnFavorite.setBackgroundResource(R.drawable.heartfull)
                model.chacked = true
                listener.onFavoriteClick(model)
                mobile?.putObject(model.id.toString(), model)
                true

            } else {
                btnFavorite.setBackgroundResource(R.drawable.heart)
                model.chacked = false
                listener.onFavoriteClick(model)
                mobile?.putObject(model.id.toString(), model)
                false
            }
        }

        itemView.setOnClickListener { listener.onMobileClick(model) }

    }


}

interface OnMobileClickListener {
    fun onMobileClick(mobile: MobileModel)
    fun onFavoriteClick(favorite: MobileModel)
}

