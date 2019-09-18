package com.example.mobilephone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.squareup.picasso.Picasso

class MobileAdapter(private val mobileList: List<MobileModel>, private val listener: OnMobileClickListener) :
    RecyclerView.Adapter<MobileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mobileList.count()
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        // Log.i("pppp", mobileList.toString())
        holder.bind(mobileList[position], listener)
    }

}

class MobileViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_mobile, parent, false)
) {

    private val iImage: ImageView = itemView.findViewById(R.id.imageMobile)
    private val txtName: TextView = itemView.findViewById(R.id.nameMobile)
    private val txtDescription: TextView = itemView.findViewById(R.id.description)
    private val txtPrice: TextView = itemView.findViewById(R.id.price)
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
            if (check === false) {
                btnFavorite.setBackgroundResource(R.drawable.heartfull)
                check = true
            } else {
                btnFavorite.setBackgroundResource(R.drawable.heart)
                check = false
            }
        }




        itemView.setOnClickListener { listener.onMobileClick(model) }

    }
//
//    private fun saveFavorite(mobile : MobileModel){
////
//    }
}

interface OnMobileClickListener {
    fun onMobileClick(mobile: MobileModel)
}