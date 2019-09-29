package com.example.mobilephone.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilephone.R
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.presenter.FavoritePresenter
import com.example.mobilephone.model.SwipeToDeleteCallback
import com.example.mobilephone.view.activity.DetailMobileActivity
import com.example.mobilephone.view.adapter.FavoriteAdapter
import com.example.mobilephone.view.contract.FavoriteInterface
import com.example.mobilephone.view.contract.MainActivityInterface
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(), FavoriteInterface, FavoriteInterface.OnClickFavoriteList {
    private lateinit var shareFavorite: ModelPreferences
    private val favoriteAdapter: FavoriteAdapter  by lazy { FavoriteAdapter(this, shareFavorite) }
    private lateinit var presenter: FavoritePresenter
    private var onListener: MainActivityInterface? = null

    companion object {
        fun newInstance(): FavoriteFragment =
            FavoriteFragment()
    }

    fun setOnListener(onListener: MainActivityInterface) {
        this.onListener = onListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { context ->
            shareFavorite = ModelPreferences(context)
            presenter = FavoritePresenter(this, shareFavorite)
            presenter.getFavorite()
        }

        rvFavorite.adapter = favoriteAdapter
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.itemAnimator = DefaultItemAnimator()
        swipeSetup()
    }

    override fun onFavoriteDetailClick(favorite: MobileModel) {
        DetailMobileActivity.startActivity(context, favorite)
    }

    override fun setMobile(favoriteList: List<MobileModel>) {
        favoriteAdapter.favoriteList(ArrayList(favoriteList))
    }


    override fun onSwipeRemove(unFav: MobileModel) {
        presenter.readFavorite.remove(unFav)
        onListener?.onRemoveSwipeFavorite(unFav)
    }

    private fun swipeSetup() {
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvFavorite.adapter as FavoriteAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvFavorite)
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
        favoriteAdapter.addFavorite(fav)
        presenter.readFavorite.add(fav)

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
        favoriteAdapter.removeHeart(remove)
        presenter.readFavorite.remove(remove)
    }

    override fun showErrorMsg(msg: String) {
        Log.e("error", msg)
    }
}

