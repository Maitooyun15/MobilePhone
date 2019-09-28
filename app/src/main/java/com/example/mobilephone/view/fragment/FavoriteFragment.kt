package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.presenter.FavoritePresenter
import com.example.mobilephone.presenter.SwipeToDeleteCallback
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.activity.onListener
import com.example.mobilephone.view.adapter.FavoriteAdapter
import com.example.mobilephone.view.adapter.OnMobileClickListener
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(), FavoriteInterface, OnMobileClickListener {
    // ไม่เอา
    override fun onRemoveHeart(remove: MobileModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // อย่าลืมเก็บค่า
    private val favoriteAdapter: FavoriteAdapter by lazy { FavoriteAdapter(this) }
    private lateinit var presenter: FavoritePresenter
    private var onListener: onListener? = null
    private var remove: ModelPreferences = ModelPreferences(context)

    override fun onRemoveClick(unFav: MobileModel) {
        presenter.data.remove(unFav)
        onListener?.onRemoveFavorite(unFav)
    }

    fun setOnListener(onListener: onListener) {
        this.onListener = onListener
    }

    // ไม่ใช้
    override fun onFavoriteClick(favorite: MobileModel) {
        // onListener?.onFavorite(favorite)
        //   Log.e("test", "test")
    }

    override fun onMobileClick(mobileModelList: MobileModel) {
        DetailMobileActivity.startActivity(context, mobileModelList)
    }

    companion object {
        fun newInstance(): FavoriteFragment =
            FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { context ->
            remove = ModelPreferences(context)
            presenter = FavoritePresenter(this, remove)
            presenter.getFavorite()
        }

        rvFavorite.adapter = favoriteAdapter
        rvFavorite.layoutManager = LinearLayoutManager(context)
        setUp()
    }

    private fun setUp() {
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvFavorite.adapter as FavoriteAdapter
                // println("9999" + viewHolder.adapterPosition)
                //  presenter.data.removeAt(viewHolder.adapterPosition)
                adapter.removeAt(viewHolder.adapterPosition, remove)
                // println(  adapter.removeAt(viewHolder.adapterPosition))
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvFavorite)
    }

    override fun setMobile(mobileModelList: List<MobileModel>) {
        favoriteAdapter.addFavorite(ArrayList(mobileModelList))
    }

    fun sortLowToHigh() {
        presenter.getSortLowToHigh()

    }

    fun sortHighToLow() {
        presenter.getSortHighToLow()
    }

    fun sortRating() {
        presenter.getSortRating()
    }

    fun addFavorite(fav: MobileModel, select: Int) {
        favoriteAdapter.addItem(fav)
        presenter.data.add(fav)


        when (select) {
            0 -> {
                presenter.getSortLowToHigh()

            }
            1 -> {
                presenter.getSortHighToLow()
            }
            2 -> {
                presenter.getSortRating()
            }

        }
    }

    fun removeHeart(remove: MobileModel) {
        favoriteAdapter.removeHeart(remove, this.remove)
        presenter.data.remove(remove)
    }
}

interface FavoriteInterface {
    fun setMobile(mobileModelList: List<MobileModel>)
}