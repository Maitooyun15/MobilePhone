package com.example.mobilephone.view.adapter

import android.util.Log
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
    private val listener: OnMobileClickListener
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    private var mobileList: ArrayList<MobileModel> = arrayListOf()

    fun addFavorite(list: ArrayList<MobileModel>) {
        mobileList = list
        notifyDataSetChanged()
    }

    fun addItem(fav: MobileModel) {
//        var isAddItem = true
//        for (i in mobileList) {
//            if (i.id == fav.id) {
//                isAddItem = false
//                break
//            }
//        }
//        if (isAddItem) {
//            mobileList.add(fav)
//            notifyDataSetChanged()
//        }
        mobileList.add(fav)
        notifyDataSetChanged()

    }

    fun removeAt(position: Int, removePref: ModelPreferences) {
        var removeFav = mobileList[position]
        mobileList.removeAt(position)
        Log.e("test", "delete Swipe เหลือ list ที่เหลือ " + mobileList.map { it.id }.toString())

        removePref.putObject("model", mobileList)
        listener.onRemoveClick(removeFav)
        notifyDataSetChanged()
    }

    fun removeHeart(model: MobileModel, removePref: ModelPreferences) {
        Log.e(
            "test",
            "ค่าก่อนลบหัวใจ " + mobileList.map { it.id }.toString() + " model " + model + "  getshare" + removePref.getObject(
                "model"
            )
        )

        mobileList.remove(model)

        Log.e("test", "ไซส์" + mobileList.size)

        Log.e("test", "delete Heart เหลือ list ที่เหลือ  " + mobileList.map { it.id }.toString())
//        listener.onRemoveClick(mobileList)

        removePref.putObject("model", mobileList)
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

    fun bind(model: MobileModel, listener: OnMobileClickListener) {

        Picasso.get()
            .load(model.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageMobile)
        txtName.text = model.id.toString()
        txtPrice.text = "${model.price}"
        txtRating.text = "Rating: ${model.rating}"

        itemView.setOnClickListener { listener.onMobileClick(model) }
    }
}


