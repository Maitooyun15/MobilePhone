package com.example.mobilephone.view.adapter

import android.util.Log
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
    private var fav: ArrayList<MobileModel> = arrayListOf()

    init {
        mobile?.getObject("model")?.let { fav.addAll(it) }
        Log.e("test", "ค่าที่มาก่อน " + fav.map { it.id }.toString())
    }

    fun addMobile(list: List<MobileModel>) {
        mobileList = list
        notifyDataSetChanged()
    }

    fun updateFavorite(removeFav: MobileModel) {

        fav.remove(removeFav)

        mobileList.forEach {
            if (it.id == removeFav.id) {
                it.checked = false
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(parent, mobile, fav)
    }

    override fun getItemCount(): Int {
        return mobileList.count()
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        holder.bind(mobileList[position], listener)
    }

}

class MobileViewHolder(parent: ViewGroup, var mobile: ModelPreferences?, var list: ArrayList<MobileModel>) :
    RecyclerView.ViewHolder(
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
        txtName.text = model.id.toString()
        txtDescription.text = model.description
        txtPrice.text = "Price: $${model.price}"
        txtRating.text = "Rating: ${model.rating}"

        val id: ArrayList<Int> = arrayListOf()
        var saveFavorite = mobile?.getObject("model")

//        if (saveFavorite != null) {
//            for (i in saveFavorite) {
//                if (!id.contains(i.id))
//                    id.add(i.id)
//            }
//        }
//        println("list id " + id.toString())

        if (model.checked) {
            btnFavorite.setBackgroundResource(R.drawable.heartfull)
        } else {
            btnFavorite.setBackgroundResource(R.drawable.heart)
        }

        btnFavorite.setOnClickListener {
            if (model.checked) {
                listener.onRemoveHeart(model)

                list.remove(model)
                model.checked = false
                btnFavorite.setBackgroundResource(R.drawable.heart)

            } else {
                btnFavorite.setBackgroundResource(R.drawable.heartfull)
                model.checked = true
                list.add(model)
                list.toMutableSet()
                Log.e("test", "ค่าตอนแอด" + list.toString())
                mobile?.putObject("model", list)
                listener.onFavoriteClick(model)

            }
        }
        itemView.setOnClickListener { listener.onMobileClick(model) }
    }


}

interface OnMobileClickListener {
    fun onMobileClick(mobile: MobileModel)
    fun onFavoriteClick(favorite: MobileModel)
    fun onRemoveClick(unFav: MobileModel)
    fun onRemoveHeart(remove: MobileModel)
}